
package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.SimulationStateDTO;
import com.traffic_simulator.interfaces.SimulationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {
    @Autowired
    private SimulationProvider simulationProvider;

    @PostMapping("/run")
    public void run(Map<Long, Long> areaIdVersionId) {
        simulationProvider.run(areaIdVersionId);
    }

    @PostMapping("/stop")
    public void stop() {
        simulationProvider.stop();
    }

    @PostMapping("/play")
    public void play() {
        simulationProvider.play();
    }

    @PostMapping("/destroy")
    public void destroy() {
        simulationProvider.destroy();
    }

    @GetMapping("/state")
    public SimulationStateDTO state() {
        return simulationProvider.state();
    }
}

