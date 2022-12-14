package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.CarPath;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PathfindingAlgorithm {
    protected enum calculationType {ALL, NATURAL, TRAFFIC}
    protected ConcurrentHashMap<GraphObject, List<CarPathPoint>> endToPathConcurrentHashMap;
    protected RoadMap roadMap;
    //private GraphObject start;
    public PathfindingAlgorithm(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public List<List<CarPath>> compute(calculationType calculationType) {
        List<List<CarPath>> result = new ArrayList<>();

        for (GraphObject start : roadMap.getBuildings()) {
            result.add(computeCarPath(calculationType, start));
        }
        return result;
    }
    protected abstract List<CarPath> computeCarPath(calculationType calculationType, GraphObject start);

}
