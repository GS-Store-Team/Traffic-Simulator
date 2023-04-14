package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Car {
    private final long id;
    private float currentVelocity;        //per tick
    private float currentAcceleration = 1;    //per tick
    private Building buildingStart;
    private Building buildingEnd;
    private Coordinates currentPosition;
    private int carLength = GlobalSettings.automobileLength;
    public Car(long id, Building buildingStart, Building buildingEnd) {
        this.id = id;
        this.buildingStart = buildingStart;
        this.buildingEnd = buildingEnd;
    }
}
