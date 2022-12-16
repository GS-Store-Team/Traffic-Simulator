package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class CarPathsGraph {
    private Node start;
    private Deque<Node> nodes;
    private Deque<Edge> edges;

    public CarPathsGraph(Node start) {
        this.start = start;
        this.start.setPathPrevNodeEdge(null);

        this.nodes = new ArrayDeque<>();
        this.edges = new ArrayDeque<>();
    }
    public CarPath retrievePath(Node end) {
        CarPath carPath = new CarPath(start, end);
        Node currentNode = end;
        while (currentNode.getPathPrevNodeEdge() != null) {
            carPath.getNodes().addFirst(currentNode);
            carPath.getEdges().addFirst(currentNode.getPathPrevNodeEdge());
            currentNode = currentNode.getPathPrevNodeEdge().getStart();
        }
        carPath.getNodes().addFirst(start);
        return carPath;
    }
    public CarPath retrievePath(Edge end) {
        return null;
    }
}
