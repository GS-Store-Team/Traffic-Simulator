package com.traffic_simulator.businnes_logic.simulation_runner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SimulationSettings {
    private final long ticksPerSecond = 10;
}
