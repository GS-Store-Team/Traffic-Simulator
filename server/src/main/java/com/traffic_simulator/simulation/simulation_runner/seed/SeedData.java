package com.traffic_simulator.simulation.simulation_runner.seed;

public record SeedData(long depTimeShift,
                       long destTimeSpend,
                       int depCarPackSize,
                       int carPackShiftAndSign,
                       int coeff) {
}
