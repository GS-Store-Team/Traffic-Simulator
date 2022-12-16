package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.CarPathsGraph;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class PathfindingAlgorithm {
    protected RoadMap roadMap;
    protected GraphMap graph;
    public PathfindingAlgorithm(RoadMap roadMap) {
        this.roadMap = roadMap;
        this.graph = new GraphMap(this.roadMap);
    }

    public List<CarPathsGraph> compute() {
        List<CarPathsGraph> result = new ArrayList<>();

        for (Node start : graph.getBuildingNodes()) {
            start.setWeightMark(0);
            result.add(computeCarPath(start));
        }
        return result;
    }
    protected abstract CarPathsGraph computeCarPath(Node start);

}
