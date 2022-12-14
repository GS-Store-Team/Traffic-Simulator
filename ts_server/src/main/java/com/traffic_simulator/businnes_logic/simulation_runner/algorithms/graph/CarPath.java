package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.CarPathPoint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@ToString
public class CarPath {
    private List<CarPathPoint> nodes;
    private HashMap<Road, Integer> sidedRoads;

    public CarPath() {
        this.nodes = new ArrayList<>();
        this.sidedRoads = new HashMap<>();
    }
}
