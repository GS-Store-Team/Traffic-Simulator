package com.traffic_simulator.businnes_logic.models.signs.road_signs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class RoadSign {
    private final String tmp = "RoadSign";
    private float beginRatio;
    private float endRatio;

    public RoadSign(float beginRatio, float endRatio) {
        this.beginRatio = beginRatio;
        this.endRatio = endRatio;
    }
}
