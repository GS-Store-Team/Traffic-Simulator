package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.MapDTO;
import com.traffic_simulator.dto.MapStateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("state")
public class SimulationController {

    @GetMapping
    public ResponseEntity<MapStateDTO> getState(){
        return ResponseEntity.ok(new MapStateDTO());
    }

    @GetMapping("/config")
    public ResponseEntity<MapDTO> getMapConfig(){
        return ResponseEntity.ok(new MapDTO());
    }
}
