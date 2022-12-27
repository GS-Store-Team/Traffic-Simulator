package com.traffic_simulator.simulation.simulation_runner.algorithms.car_path;

import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@ToString
public class CarPath {
    private final NodeNe start;
    private final Deque<NodeNe> nodes;
    private final NodeNe end;

    public CarPath(NodeNe start, NodeNe end) {
        this.start = start;
        this.nodes = new ArrayDeque<>();
        this.end = end;
    }
}