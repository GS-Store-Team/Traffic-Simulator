package com.traffic_simulator.businnes_logic.simulation_runner;

public record TickGeneratorSettings(long timeSpeedMultiplier) {
    public TickGeneratorSettings(long timeSpeedMultiplier) {
        if (timeSpeedMultiplier > 0) {
            this.timeSpeedMultiplier = timeSpeedMultiplier;
        } else {
            this.timeSpeedMultiplier = 1;
        }
    }
}
