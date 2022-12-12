package com.traffic_simulator.businnes_logic.simulation_runner;

import com.traffic_simulator.businnes_logic.beans.SimulationContext;
import com.traffic_simulator.dto.MapDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Lazy
public class SimulationRunner {
    private final SimulationContext simulationContext;

    public void start() {

    }

    public MapDTO getCurrentState() {

        return null;
    }
}
