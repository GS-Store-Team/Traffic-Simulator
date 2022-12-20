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
    private int currentVelocity;        //per tick
    private int currentAcceleration = 1;    //per tick
    private Building buildingStart;
    private Building buildingEnd;
    public Car(Building buildingStart, Building buildingEnd) {
        this.buildingStart = buildingStart;
        this.buildingEnd = buildingEnd;
    }
}
