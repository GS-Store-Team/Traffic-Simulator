package com.traffic_simulator.businnes_logic.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Setter
@Getter
@ToString
public abstract class GraphObject {
    protected enum ElementColor {WHITE, GRAY, BLACK}
    protected int naturalWeight;
    protected Map<Integer, Integer> trafficWeight;
    protected ElementColor mark;
    protected int markWeight;
    public GraphObject() {
        this.naturalWeight = 0;
        this.trafficWeight = new HashMap<>();
        this.mark = ElementColor.WHITE;
        this.markWeight = 0;
    }
}
