package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.models.buildings.Building;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Car {
    private int currentVelocity;        //per tick
    private int currentAcceleration = 1;    //per tick
    private Building buildingStart;
    private Building buildingEnd;
    public Car(Building buildingStart, Building buildingEnd) {
        this.buildingStart = buildingStart;
        this.buildingEnd = buildingEnd;
    }
}
