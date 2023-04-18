package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.simulation_runner.seed.SeedData;
import com.traffic_simulator.simulation.simulation_runner.seed.SeedDecoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationSettings {
    private int automobileMaxAcceleration = 3;
    private int automobileMinAcceleration = -10;
    private double truckMaxAcceleration = 3;
    private double truckMinAcceleration = -8;
    private SeedData seedData = SeedDecoder.decode(1234567891234567L);
}
