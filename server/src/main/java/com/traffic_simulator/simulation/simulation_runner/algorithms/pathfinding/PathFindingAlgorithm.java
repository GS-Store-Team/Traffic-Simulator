
package com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding;

import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.PathsConstructionException;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.car_path.CarPathsBunch;
import lombok.NonNull;

import java.util.HashMap;

public abstract class PathFindingAlgorithm {
    protected AreaGraph graph;

    /*public PathFindingAlgorithm(@NonNull AreaGraph graph) {
        this.graph = graph;
    }*/

    public PathFindingAlgorithm() {
    }

    public HashMap<Node, CarPathsBunch> compute(AreaGraph graph) throws GraphConstructionException, PathsConstructionException {
        this.graph = graph;
        HashMap<Node, CarPathsBunch> result = new HashMap<>();

        if (graph == null) {
            throw new PathsConstructionException("No graph provided.", null);
        }

        try {
            for (Node start : graph.getNodesSet()) {
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

