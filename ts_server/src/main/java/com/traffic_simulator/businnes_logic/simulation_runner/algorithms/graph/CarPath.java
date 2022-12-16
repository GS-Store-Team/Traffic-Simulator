package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@ToString
public class CarPath {
    private Node start;
    private Deque<Node> nodes;
    private Deque<Edge> edges;
    private Node end;

    public CarPath(Node start, Node end) {
        this.start = start;
        this.nodes = new ArrayDeque<>();
        this.edges = new ArrayDeque<>();
        this.end = end;
    }
}