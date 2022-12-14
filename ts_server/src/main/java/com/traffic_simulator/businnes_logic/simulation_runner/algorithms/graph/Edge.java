package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.ElementColor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Edge {
    private double weight = 0;
    private Node startNode = null;
    private Node endNode = null;
    private ElementColor color = ElementColor.WHITE;
}
