package com.traffic_simulator.businnes_logic.models.car;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.supportive.cell.Cell;
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

    private GraphObject currentGraphObject;
    private GraphObject nextGraphObject;

    private List<Cell> advance;

    public Navigator(GraphObject initGraphObject, double accelerationUpperLimit, double accelerationLowerLimit) {
        this.acceleration = 0;
        this.accelerationUpperLimit = accelerationUpperLimit;
        this.accelerationLowerLimit = accelerationLowerLimit;
        this.currentGraphObject = initGraphObject;
        this.nextGraphObject = null;
        this.advance = new ArrayList<>();
    }
}
