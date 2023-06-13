
package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.exceptions.SimulationException;
import com.traffic_simulator.simulation.GlobalSettings;
import lombok.Getter;

import java.util.concurrent.TimeUnit;
@Getter
public class TickGenerator implements Runnable {
    private SimulationRunner simulationRunner;
    private long ticksPerSecond = GlobalSettings.ticksPerSecond;
    private boolean running;

    public TickGenerator(SimulationRunner simulationRunner) {
        this.simulationRunner = simulationRunner;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (running) {
                    try {
                        simulationRunner.update();
                    } catch (SimulationException exc) {
                        this.stop();
                        System.out.println(exc.getMessage());
                    }
                    System.out.println("tic");

                    TimeUnit.MILLISECONDS.sleep(1000 / ticksPerSecond);     //inside of the if block! Important point in debugging!
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

