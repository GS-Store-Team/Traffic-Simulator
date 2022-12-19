package com.traffic_simulator.simulation.simulation_runner.algorithms.car_path;

import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@ToString
public class CarPath {
    private final Node start;
    private final Deque<Node> nodes;
    private final Deque<Edge> edges;
    private final Node end;

    public CarPath(Node start, Node end) {
        this.start = start;
        this.nodes = new ArrayDeque<>();
        this.edges = new ArrayDeque<>();
        this.end = end;
    }
}