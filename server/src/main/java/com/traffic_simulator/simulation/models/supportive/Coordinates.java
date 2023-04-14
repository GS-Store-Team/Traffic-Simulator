package com.traffic_simulator.simulation.models.supportive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates{
    private float x;
    private float y;

    public Coordinates(float x, float y){
        this.x = x;
        this.y = y;
    }
}