package com.traffic_simulator.simulation.models.car.types;

import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.car.Car;

public class Truck extends Car {

    //private static final int cellLength = GlobalSettings.truckLength;

    public Truck(MapObject start, MapObject destination) {
        super(start, destination);
    }
}
