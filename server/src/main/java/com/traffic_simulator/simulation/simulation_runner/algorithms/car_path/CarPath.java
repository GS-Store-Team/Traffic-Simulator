package com.traffic_simulator.simulation.simulation_runner.algorithms.car_path;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.models.road.Road;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@ToString
public class CarPath {
    private final Node start;
    private final Deque<Node> nodes;
    private final Node end;
    private Deque<Road> roads;

    public CarPath(Node start, Node end) {
        this.start = start;
        this.nodes = new ArrayDeque<>();
        this.end = end;
        this.roads = new ArrayDeque<>();
    }

    public void addNode(Node node) {
        //Node nodeNe = new Node(node);
        nodes.addFirst(node);
    }
}