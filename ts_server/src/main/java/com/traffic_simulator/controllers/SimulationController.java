package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.MapDTO;
import com.traffic_simulator.dto.MapStateDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("state")
public class SimulationController {

    @GetMapping
    public MapStateDTO getState(){
        return new MapStateDTO();
    }

    @GetMapping("/config")
    public MapDTO getMapConfig(){
        return new MapDTO();
    }
}
