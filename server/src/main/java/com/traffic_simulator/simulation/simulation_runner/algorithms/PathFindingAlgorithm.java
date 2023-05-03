
package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.PathsConstructionException;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.simulation.graph.AreaGraph;
import lombok.NonNull;
import java.util.HashMap;

public abstract class PathFindingAlgorithm {
    protected AreaGraph graph;
    public PathFindingAlgorithm(@NonNull AreaGraph graph) {
        this.graph = graph;
    }

    public HashMap<Node, CarPathsBunch> compute() throws GraphConstructionException {
        HashMap<Node, CarPathsBunch> result = new HashMap<>();

        try {
            for (Node start : graph.getNodes()) {
                result.put(start, computeCarPath(start));
                graph.resetWeights();
            }
            System.out.println("Paths computation completed!");
            return result;
        } catch (PathsConstructionException exc) {
            throw new GraphConstructionException("Graph construction error!", exc.getUnreachableNodes());
        }
    }
    protected abstract CarPathsBunch computeCarPath(Node start) throws PathsConstructionException;
}

