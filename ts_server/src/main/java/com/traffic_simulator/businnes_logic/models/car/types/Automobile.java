package com.traffic_simulator.businnes_logic.models.car.types;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.MapObject;
import com.traffic_simulator.businnes_logic.models.car.Car;

public class Automobile extends Car {

    private static final int cellLength = GlobalSettings.automobileLength;

    public Automobile(MapObject start, MapObject destination) {
        super(start, destination);
    }
}
