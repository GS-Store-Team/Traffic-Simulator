package com.traffic_simulator.businnes_logic.models.buildings;

import com.traffic_simulator.businnes_logic.models.car.Car;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ParkingZone {
    private int capacity;
    private List<Car> cars;

    private Coordinates upLeftCorner;
    private Coordinates downRightCorner;

    public ParkingZone(int capacity, Coordinates upLeftCorner, Coordinates downRightCorner) {
        this.capacity = capacity;
        this.cars = new ArrayList<>();
        this.upLeftCorner = upLeftCorner;
        this.downRightCorner = downRightCorner;
    }
}
