package com.traffic_simulator.simulation.models.supportive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates{
    private double x;
    private double y;

    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }
}