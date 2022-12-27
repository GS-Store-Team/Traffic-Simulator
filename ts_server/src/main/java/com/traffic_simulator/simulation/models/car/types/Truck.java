package com.traffic_simulator.simulation.models.car.types;

import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.car.Car;

public class Truck extends Car {

    //private static final int cellLength = GlobalSettings.truckLength;

    public Truck(long id, Building start, Building destination) {
        super(id, start, destination);
    }
}
