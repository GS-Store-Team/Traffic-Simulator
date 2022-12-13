package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.models.GraphObject;

public record Node(GraphObject element,
                   Node prevNode,
                   ElementColor color,
                   double weight) {
    public Node {
    }
}
