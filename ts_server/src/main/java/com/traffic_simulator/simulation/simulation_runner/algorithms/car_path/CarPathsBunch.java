package com.traffic_simulator.simulation.simulation_runner.algorithms.car_path;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class CarPathsBunch {
    private final NodeNe start;
    private final HashMap<NodeNe, CarPath> carPathsEndsMap;

    public CarPathsBunch(NodeNe start) {
        this.start = start;
        this.carPathsEndsMap = new HashMap<>();
    }
}
