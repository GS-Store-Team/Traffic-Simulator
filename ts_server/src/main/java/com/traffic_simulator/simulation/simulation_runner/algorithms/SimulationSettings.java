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
    public double automobileMaxAcceleration = 3;
    public double automobileMinAcceleration = -10;
    public double truckMaxAcceleration = 3;
    public double truckMinAcceleration = -8;
    public SeedData seedData = SeedDecoder.decode(1234567891234567L);
}
