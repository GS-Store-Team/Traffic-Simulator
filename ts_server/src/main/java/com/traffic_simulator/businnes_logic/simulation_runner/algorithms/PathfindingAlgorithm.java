package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.models.RoadMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.Node;
import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.PathsConstructionException;
import lombok.NonNull;

import java.util.HashMap;

public abstract class PathfindingAlgorithm {
    protected RoadMap roadMap;
    protected GraphMap graph;
    public PathfindingAlgorithm(@NonNull GraphMap graph) {
        this.graph = graph;
    }

    public HashMap<Node, CarPathsBunch> compute() throws GraphConstructionException {
        HashMap<Node, CarPathsBunch> result = new HashMap<>();

        try {
            for (Node start : graph.getBuildingNodes()) {
                start.setWeightMark(0);
                result.put(start, computeCarPath(start));
            }
            return result;
        } catch (PathsConstructionException exc) {
            throw new GraphConstructionException("Graph construction error!", exc.getUnreachableNodes());
        }
    }
    protected abstract CarPathsBunch computeCarPath(Node start) throws PathsConstructionException;
}
