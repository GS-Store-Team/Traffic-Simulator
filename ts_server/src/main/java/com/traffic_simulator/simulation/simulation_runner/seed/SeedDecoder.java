package com.traffic_simulator.simulation.simulation_runner.seed;

import com.traffic_simulator.simulation.GlobalSettings;

public class SeedDecoder {

    public static SeedData decode(long seed) {
        return decode(String.valueOf(seed));
    }

    public static SeedData decode(String seed) {

        if (seed.length() != GlobalSettings.seedLength) {
            throw new NumberFormatException("Incorrect seed: must have length of" + GlobalSettings.seedLength + " symbols!");
        }

        try {
            Long.parseLong(seed);
            return new SeedData(
                    Long.parseLong(seed.substring(0, 5)),
                    Long.parseLong(seed.substring(5, 10)),
                    Integer.parseInt(seed.substring(10, 12)),
                    Integer.parseInt(seed.substring(12, 13)),
                    Integer.parseInt(seed.substring(13, 16))
            );
        } catch (NumberFormatException exc) {
            throw new NumberFormatException("Incorrect seed: must contain only digits!");
        }

    }
}
