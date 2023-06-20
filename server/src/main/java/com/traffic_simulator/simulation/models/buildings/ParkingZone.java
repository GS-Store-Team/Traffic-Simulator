package com.traffic_simulator.simulation.models.buildings;

import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ParkingZone implements Serializable {
    private int capacity;
    private List<Car> cars;

    private Coordinates upLeftCorner;

    public ParkingZone(int capacity, Coordinates upLeftCorner) {
        this.capacity = capacity;
        this.cars = new ArrayList<>();
        this.upLeftCorner = upLeftCorner;
    }

    public boolean addCar(Car car) {
        if (cars.size() + 1 <= capacity) {
            cars.add(car);
            return true;
        } else {
            return false;
        }
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }
}
