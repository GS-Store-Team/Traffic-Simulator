package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class Navigator {
    private double acceleration;
    private double accelerationUpperLimit;
    private double accelerationLowerLimit;

    private MapObject currentMapObject;
    private MapObject nextMapObject;

    private List<Cell> advance;

    public Navigator(Car car, double accelerationUpperLimit, double accelerationLowerLimit) {
        this.acceleration = 0;
        this.accelerationUpperLimit = accelerationUpperLimit;
        this.accelerationLowerLimit = accelerationLowerLimit;
        //this.currentMapObject = car.getCurrentMapObject();
        this.nextMapObject = null;
        this.advance = new ArrayList<>();
    }

    public void update() {

    }
}
