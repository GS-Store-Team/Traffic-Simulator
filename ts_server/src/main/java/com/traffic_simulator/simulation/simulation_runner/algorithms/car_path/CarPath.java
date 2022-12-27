package com.traffic_simulator.simulation.simulation_runner.algorithms.car_path;

import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.road.Road;
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
    private Deque<Road> roads;

    public CarPath(NodeNe start, NodeNe end) {
        this.start = start;
        this.nodes = new ArrayDeque<>();
        this.end = end;
        this.roads = new ArrayDeque<>();
    }

    public void addNode(NodeNe node) {
        NodeNe nodeNe = new NodeNe(node);
        nodes.addFirst(node);
    }
}