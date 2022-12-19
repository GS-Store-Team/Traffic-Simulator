package com.traffic_simulator.simulation.models.car.types;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.car.Car;

public class Automobile extends Car {

    private static final int cellLength = GlobalSettings.automobileLength;

    public Automobile(MapObject start, MapObject destination) {
        super(start, destination);
    }
}
