package com.traffic_simulator.models;

import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.road.Road;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class AreaVersion {
    private Long id;
    private Long owner;
    private Boolean locked;
    private Timestamp created;
    private Timestamp edited;
    private List<Building> buildings;
    private List<Road> roads;
}
