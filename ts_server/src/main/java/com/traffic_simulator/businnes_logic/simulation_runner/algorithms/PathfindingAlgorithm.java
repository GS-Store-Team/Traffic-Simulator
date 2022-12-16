package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.CarPathsBunch;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.Node;

import java.util.HashMap;

public abstract class PathfindingAlgorithm {
    protected RoadMap roadMap;
    protected GraphMap graph;
    public PathfindingAlgorithm(RoadMap roadMap) {
        this.roadMap = roadMap;
        this.graph = new GraphMap(this.roadMap);
    }

    public HashMap<Node, CarPathsBunch> compute() {
        HashMap<Node, CarPathsBunch> result = new HashMap<>();

        for (Node start : graph.getBuildingNodes()) {
            start.setWeightMark(0);
            result.put(start, computeCarPath(start));
        }
        return result;
    }
    protected abstract CarPathsBunch computeCarPath(Node start);
}
