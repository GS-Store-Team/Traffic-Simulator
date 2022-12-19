package com.traffic_simulator.simulation.simulation_runner.algorithms.car_path;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class CarPathsBunch {
    private final Node start;
    private final HashMap<Node, CarPath> carPathsEndsMap;

    public CarPathsBunch(Node start) {
        this.start = start;
        this.carPathsEndsMap = new HashMap<>();
    }
}
