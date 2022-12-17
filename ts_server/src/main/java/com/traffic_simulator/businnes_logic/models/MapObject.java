package com.traffic_simulator.businnes_logic.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Setter
@Getter
@ToString
public abstract class MapObject {
    protected double naturalWeight;
    protected Map<Integer, Double> trafficWeight;
    public MapObject() {
        this.naturalWeight = 0;
        this.trafficWeight = new HashMap<>();
    }

    /**
     * Calculate traffic weight.
     * @return traffic weight distinguished to lane packs (if it is road).
     */
    public abstract Map<Integer, Double> getTrafficWeight();
}
