package com.traffic_simulator.controllers;

import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.simulation.models.SimulationState;
import com.traffic_simulator.simulation.context.SimulationContext;
import com.traffic_simulator.simulation.simulation_runner.SimulationRunner;
import com.traffic_simulator.simulation.simulation_runner.TickGenerator;
import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.dto.MapStateDTO;
import com.traffic_simulator.simulation.simulation_runner.algorithms.PathFindingAlgorithm;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;
import com.traffic_simulator.simulation.simulation_runner.algorithms.StraightDijkstraAlgorithm;
import com.traffic_simulator.simulation.graph.GraphMap;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("state")
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationContext simulationContext;
    private SimulationRunner simulationRunner;
    private SimulationState roadMap;
    private TickGenerator tickGenerator;
    private PathFindingAlgorithm pathFindingAlgorithm;
    private GraphMap graphMap;

    @SneakyThrows
    @PostConstruct
    private void init(){
        this.graphMap = new GraphMap(simulationContext);

        try {
            graphMap.constructGraphMap();
        }
        catch (InvalidMapException e){
            System.out.println("Ya budu ispravlyat' " + e.getMessage());
        }

        this.pathFindingAlgorithm = new StraightDijkstraAlgorithm(this.graphMap);
        this.roadMap = new SimulationState(graphMap, pathFindingAlgorithm);
        this.simulationRunner = new SimulationRunner(roadMap, new SimulationSettings());
        this.tickGenerator = new TickGenerator(simulationRunner);
        new Thread(tickGenerator).start();
    }

    @GetMapping("/run")
    public ResponseEntity<?> startSimulation() {
        if(simulationRunner == null) init();
        else simulationRunner.reset();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/stop")
    public ResponseEntity<?> stopSimulation(){
        if(simulationRunner == null) return ResponseEntity.badRequest().build();
        tickGenerator.stop();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/play")
    public ResponseEntity<?> playSimulation(){
        if(simulationRunner == null) return ResponseEntity.badRequest().build();
        tickGenerator.play();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<SimulationDTO> getState(){
        var simulationState = simulationRunner.getCurrentSimulationState();
        return simulationState != null?
                ResponseEntity.ok(simulationState):
                ResponseEntity.noContent().build();
    }

    @GetMapping("/config")
    public ResponseEntity<MapStateDTO> getMapConfig(){
        System.out.println("Hey!");
        var mapState = graphMap.getCurrentMapConfig();
        return mapState != null?
                ResponseEntity.ok(mapState):
                ResponseEntity.noContent().build();
    }
}