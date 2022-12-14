package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.ElementColor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Node {
    private double weight = 0;
    private List<Edge> entryEdges = new ArrayList<>();
    private List<Edge> outputEdges = new ArrayList<>();
    private ElementColor color = ElementColor.WHITE;
}
