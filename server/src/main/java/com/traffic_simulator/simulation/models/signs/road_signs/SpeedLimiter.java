package com.traffic_simulator.simulation.models.signs.road_signs;

public class SpeedLimiter extends RoadSign {
    private int speedLimit;

    public SpeedLimiter(float beginRatio, float endRatio, int speedLimit) {
        super(beginRatio, endRatio);
        this.speedLimit = speedLimit;
    }
}
