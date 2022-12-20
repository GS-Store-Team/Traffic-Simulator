package com.traffic_simulator.simulation.models.car.types;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.car.Car;

public class Automobile extends Car {

    private static final int cellLength = GlobalSettings.automobileLength;

    public Automobile(Building start, Building destination) {
        super(start, destination);
    }
}
