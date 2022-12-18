package com.traffic_simulator.businnes_logic.simulation_runner;

import lombok.Getter;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TickGenerator implements Runnable {
    private SimulationRunner simulationRunner;
    @Getter
    private Instant timePassed;
    @Getter
    private long tickMoment = 1;
    private boolean running = false;

    public TickGenerator(SimulationRunner simulationRunner, TickGeneratorSettings tickGeneratorSettings) {
        this.simulationRunner = simulationRunner;
        this.tickMoment = 1000 / tickGeneratorSettings.timeSpeedMultiplier();
        this.timePassed = Instant.ofEpochSecond(0);
        reset();
    }

    @Override
    public void run() {
        reset();
        Instant currentTime;
        while (running) {
            currentTime = Instant.now();
            timePassed = currentTime;
            if (currentTime.getNano() % tickMoment < 10) {
                simulationRunner.update();
            }
        }
    }

    public void play() {
        running = true;
        run();
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        stop();
        timePassed = Instant.ofEpochSecond(0);
        simulationRunner.reset();
    }
}
