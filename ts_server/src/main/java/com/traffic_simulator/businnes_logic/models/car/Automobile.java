package com.traffic_simulator.businnes_logic.models.car;

import com.traffic_simulator.businnes_logic.models.GraphObject;

public class Automobile extends Car {

    private static final int cellLength = GlobalSettings.automobileLength;

    public Automobile(GraphObject start, GraphObject destination) {
        super(start, destination);
    }
}
