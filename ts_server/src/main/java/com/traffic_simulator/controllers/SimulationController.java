package com.traffic_simulator.controllers;

import com.traffic_simulator.businnes_logic.models.RoadMap;
import com.traffic_simulator.businnes_logic.beans.SimulationContext;
import com.traffic_simulator.businnes_logic.simulation_runner.SimulationRunner;
import com.traffic_simulator.businnes_logic.simulation_runner.TickGenerator;
import com.traffic_simulator.businnes_logic.simulation_runner.TickGeneratorSettings;
import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.dto.MapStateDTO;
import com.traffic_simulator.exceptions.SimulationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
    private RoadMap roadMap;
    private TickGenerator tickGenerator;

    @PostConstruct
    public void init(){
        this.tickGenerator = new TickGenerator();
        this.tickGenerator.init(simulationRunner, new TickGeneratorSettings(1));
        this.roadMap = new RoadMap(simulationContext);
    }

    @GetMapping("/run")
    public ResponseEntity<?> startSimulation() throws SimulationException {
        simulationRunner = new SimulationRunner(roadMap, tickGenerator);
        simulationRunner.start();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/stop")
    public ResponseEntity<?> stopSimulation(){
        if(simulationRunner == null) return ResponseEntity.badRequest().build();
        simulationRunner.stop();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/play")
    public ResponseEntity<?> playSimulation(){
        if(simulationRunner == null) return ResponseEntity.badRequest().build();
        simulationRunner.play();
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
        var mapState = roadMap.getCurrentMapConfig();
        return mapState != null?
                ResponseEntity.ok(mapState):
                ResponseEntity.noContent().build();
    }
}