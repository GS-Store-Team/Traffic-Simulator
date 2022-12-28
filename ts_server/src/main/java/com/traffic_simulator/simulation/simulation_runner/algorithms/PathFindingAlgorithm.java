package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.PathsConstructionException;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.simulation.graph.GraphMap;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import lombok.NonNull;
import java.util.HashMap;

public abstract class PathFindingAlgorithm {
    protected GraphMap graph;
    public PathFindingAlgorithm(@NonNull GraphMap graph) {
        this.graph = graph;
    }

    public HashMap<NodeNe, CarPathsBunch> compute() throws GraphConstructionException {
        HashMap<NodeNe, CarPathsBunch> result = new HashMap<>();

        try {
            for (NodeNe start : graph.getNodes()) {
                result.put(start, computeCarPath(start));
                graph.resetWeights();
            }
            System.out.println("Paths computation completed!");
            return result;
        } catch (PathsConstructionException exc) {
            throw new GraphConstructionException("Graph construction error!", exc.getUnreachableNodes());
        }
    }
    protected abstract CarPathsBunch computeCarPath(NodeNe start) throws PathsConstructionException;
}