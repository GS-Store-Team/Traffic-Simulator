package com.traffic_simulator.businnes_logic.models.car.types;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.car.Car;

public class Automobile extends Car {

    private static final int cellLength = GlobalSettings.automobileLength;

    public Automobile(GraphObject start, GraphObject destination) {
        super(start, destination);
    }
}
