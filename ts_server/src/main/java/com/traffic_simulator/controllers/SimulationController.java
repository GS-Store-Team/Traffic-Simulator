package com.traffic_simulator.controllers;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.beans.SimulationContext;
import com.traffic_simulator.businnes_logic.simulation_runner.SimulationRunner;
import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.dto.MapStateDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("state")
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationContext simulationContext;
    private SimulationRunner simulationRunner;
    private RoadMap roadMap;

    @PostConstruct
    public void init(){
        this.roadMap = new RoadMap(simulationContext);
    }

    @GetMapping("/run")
    public ResponseEntity<?> startSimulation(){
        simulationRunner = new SimulationRunner(roadMap);
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