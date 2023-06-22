
package com.traffic_simulator.simulation.simulation_runner;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public class TickGenerator implements Runnable {
    private Simulation simulation;
    private long fps = 1;
    private boolean running;

    public TickGenerator(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (running) {
                    simulation.update();
                    TimeUnit.MILLISECONDS.sleep(1000 / fps);
                } else {
                    TimeUnit.MILLISECONDS.sleep(200);
                }
            }
        } catch (InterruptedException ignore) {
        }
    }

    public void play() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}
