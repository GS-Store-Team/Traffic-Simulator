package com.traffic_simulator.businnes_logic.models.car;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Car {
    private Navigator navigator;

    private int currentVelocity;
    private int currentAcceleration;

    private GraphObject start;
    private GraphObject destination;

    private GraphObject currentGraphObject;
    private boolean hasPath;
    public Car(GraphObject start, GraphObject destination) {
        this.currentVelocity = 0;
        this.currentAcceleration = 0;
        this.hasPath = false;
        this.start = start;
        this.destination = destination;
        this.currentGraphObject = start;
        this.navigator = new Navigator(start, GlobalSettings.automobileMaxAcceleration, GlobalSettings.automobileMinAcceleration);
    }

    public void update() {

    }

    private void changeSpeed() {

    }
}
