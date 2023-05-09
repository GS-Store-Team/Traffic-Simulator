
package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.AreaGraphSimulationStateDTO;
import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.simulation.context.AreaSimulationContext;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.models.SimulationState;
import com.traffic_simulator.simulation.simulation_runner.SimulationRunner;
import com.traffic_simulator.simulation.simulation_runner.TickGenerator;
import com.traffic_simulator.simulation.simulation_runner.algorithms.PathFindingAlgorithm;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;
import com.traffic_simulator.simulation.simulation_runner.algorithms.StraightDijkstraAlgorithm;
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
    private final AreaSimulationContext areaSimulationContext;
    private SimulationRunner simulationRunner;
    private SimulationState roadMap;
    private TickGenerator tickGenerator;
    private PathFindingAlgorithm pathFindingAlgorithm;
    private AreaGraph areaGraph;

    @SneakyThrows
    @PostConstruct
    private void init() {
        this.areaGraph = new AreaGraph(areaSimulationContext);

        try {
            areaGraph.constructGraphMap();
        } catch (InvalidMapException e) {
            System.out.println("Ya budu ispravlyat' " + e.getMessage());
        }

        this.pathFindingAlgorithm = new StraightDijkstraAlgorithm(this.areaGraph);
        this.roadMap = new SimulationState(areaGraph, pathFindingAlgorithm);
        this.simulationRunner = new SimulationRunner(roadMap, new SimulationSettings());
        this.tickGenerator = new TickGenerator(simulationRunner);
    }

    @GetMapping("/run")
    public ResponseEntity<?> startSimulation() {
        if (simulationRunner == null) {
            init();
        } else simulationRunner.reset();

        new Thread(tickGenerator).start();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/stop")
    public ResponseEntity<?> stopSimulation() {
        if (simulationRunner == null) return ResponseEntity.badRequest().build();
        tickGenerator.stop();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/play")
    public ResponseEntity<?> playSimulation() {
        if (simulationRunner == null) return ResponseEntity.badRequest().build();
        tickGenerator.play();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/area_state")
    public ResponseEntity<AreaGraphSimulationStateDTO> getState() {
        return null;
        /*var simulationState = simulationRunner.;
        return simulationState != null ?
                ResponseEntity.ok(simulationState) :
                ResponseEntity.noContent().build();*/
    }

    /*@GetMapping("/config")
    public ResponseEntity<FullMapDTO> getMapConfig() {
        var mapState = areaGraph.getCurrentMapConfig();
        return mapState != null ?
                ResponseEntity.ok(mapState) :
                ResponseEntity.noContent().build();
    }*/


}

