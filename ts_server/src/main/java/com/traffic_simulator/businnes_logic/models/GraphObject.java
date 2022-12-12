package com.traffic_simulator.businnes_logic.models;

import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.road.Road;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Setter
@Getter
@ToString
public abstract class GraphObject {
    protected enum ElementColor {WHITE, GRAY, BLACK}

    protected double naturalWeight;
    protected Map<Integer, Double> trafficWeight;
    protected ElementColor mark;
    protected double markWeight;

    public GraphObject() {
        this.naturalWeight = 0;
        this.trafficWeight = new HashMap<>();
        this.mark = ElementColor.WHITE;
        this.markWeight = 0;
    }

    /**
     * Calculate traffic weight.
     * @return traffic weight distinguished to lane packs (if it is road).
     */
    public abstract Map<Integer, Double> getTrafficWeight();
}
