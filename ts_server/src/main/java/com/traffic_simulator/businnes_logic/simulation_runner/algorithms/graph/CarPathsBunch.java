package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class CarPathsBunch {
    private final Node start;
    private final HashMap<Node, CarPath> carPathsEndsMap;

    public CarPathsBunch(Node start) {
        this.start = start;
        this.carPathsEndsMap = new HashMap<>();
    }
}
