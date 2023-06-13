package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.simulation_runner.seed.SeedData;
import com.traffic_simulator.simulation.simulation_runner.seed.SeedDecoder;
import lombok.Data;

@Data
public class SimulationSettings {
    public static final float automobileMaxAcceleration = 3;
    public static final float automobileMinAcceleration = -10;
    public static final float automobileMaxVelocity = 20;
    public static final float automobileMinVelocity = -5;

    public static final float truckMaxAcceleration = 3;
    public static final float truckMinAcceleration = -8;
    public final SeedData seedData = SeedDecoder.decode(1234567891234567L);
}
