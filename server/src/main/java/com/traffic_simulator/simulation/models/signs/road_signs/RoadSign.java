package com.traffic_simulator.simulation.models.signs.road_signs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class RoadSign implements Serializable {
    private final String tmp = "RoadSign";
    private float beginRatio;
    private float endRatio;

    public RoadSign(float beginRatio, float endRatio) {
        this.beginRatio = beginRatio;
        this.endRatio = endRatio;
    }
}
