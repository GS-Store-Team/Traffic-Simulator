package com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.car_path;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Getter
@ToString
public class CarPathsBunch implements Serializable {
    private final Node start;
    private final HashMap<Node, CarPath> carPathsByEnds;

    public CarPathsBunch(Node start) {
        this.start = start;
        this.carPathsByEnds = new HashMap<>();
    }
}
