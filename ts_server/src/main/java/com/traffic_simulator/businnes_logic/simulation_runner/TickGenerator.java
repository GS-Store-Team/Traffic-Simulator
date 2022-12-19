package com.traffic_simulator.businnes_logic.simulation_runner;

import lombok.Getter;
import java.util.concurrent.TimeUnit;

@Getter
public class TickGenerator implements Runnable {
    private SimulationRunner simulationRunner;
    private long ticksPerSecond;
    private boolean running;

    public TickGenerator(SimulationRunner simulationRunner, long ticksPerSecond) {
        this.simulationRunner = simulationRunner;
        this.ticksPerSecond = ticksPerSecond;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if(running) simulationRunner.update();
                TimeUnit.MILLISECONDS.sleep(1000/ticksPerSecond);
            }
        } catch (InterruptedException ignore){};
    }

    public void play() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}
