package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.attachment_point.Crossroad;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPath;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Car {
    private Navigator navigator;

    private int currentVelocity;        //per tick
    private int currentAcceleration;    //per tick

    private MapObject start;
    private MapObject destination;

    private MapObject currentMapObject;
    private CarPath path;
    private Instant departingTime = null;
    private List<Cell> cellDisposition;

    //TODO Продумать как назначить путь и как сменять клетки и mapobject-ы по пути
    public Car(MapObject start, MapObject destination) {
        this.currentVelocity = 0;
        this.currentAcceleration = 0;
        this.start = start;
        this.destination = destination;
        this.currentMapObject = start;
        this.cellDisposition = new ArrayList<>();
        //this.navigator = new Navigator(this, GlobalSettings.automobileMaxAcceleration, GlobalSettings.automobileMinAcceleration);
    }

    public void update() {

    }

    public void move() {
        if (currentMapObject.getClass().equals(Road.class)) {

        } else if (currentMapObject.getClass().equals(Crossroad.class)) {

        } else if (currentMapObject.getClass().equals(AttachmentPoint.class)) {

        } else if (currentMapObject.getClass().equals(Building.class)) {

        }
    }

    private void changeSpeed() {

    }
}
