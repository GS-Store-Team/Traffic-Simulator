package com.traffic_simulator.businnes_logic.models.car;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.MapObject;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.car_path.CarPath;
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

    private MapObject start;
    private MapObject destination;

    private MapObject currentMapObject;
    private CarPath path;
    //TODO Продумать как назначить путь и как сменять клетки и mapobject-ы по пути
    public Car(MapObject start, MapObject destination) {
        this.currentVelocity = 0;
        this.currentAcceleration = 0;
        this.start = start;
        this.destination = destination;
        this.currentMapObject = start;
        this.navigator = new Navigator(start, GlobalSettings.automobileMaxAcceleration, GlobalSettings.automobileMinAcceleration);
    }

    public void update() {

    }

    private void changeSpeed() {

    }
}
