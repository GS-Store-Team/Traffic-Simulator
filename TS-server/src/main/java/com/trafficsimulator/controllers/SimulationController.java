package com.trafficsimulator.controllers;

import com.trafficsimulator.models.MapLayout;
import com.trafficsimulator.models.State;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("state")
public class SimulationController {

    @GetMapping
    public State getState(){
        return new State();
    }

    @GetMapping("/config")
    public MapLayout getMapConfig(){
        return new MapLayout();
    }
}
