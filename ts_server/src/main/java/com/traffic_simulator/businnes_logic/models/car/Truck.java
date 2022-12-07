package com.traffic_simulator.businnes_logic.models.car;

import com.traffic_simulator.businnes_logic.models.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.GraphObject;

public class Truck extends Car {

    private static final int cellLength = GlobalSettings.truckLength;

    public Truck(GraphObject start, GraphObject destination) {
        super(start, destination);
    }
}
