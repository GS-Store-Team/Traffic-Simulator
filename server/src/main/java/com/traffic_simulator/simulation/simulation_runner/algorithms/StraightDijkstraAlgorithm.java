
package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.exceptions.PathsConstructionException;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;

import java.util.*;

//TODO Придумать в каком виде извлекать путь
public class StraightDijkstraAlgorithm extends PathFindingAlgorithm {

    /**
     * Common  <a href="https://e-maxx.ru/algo/dijkstra">Dijkstra algorithm</a>.<br>
     * Called "straight" because builds routes without loops.
     */
    public StraightDijkstraAlgorithm(AreaGraph areaGraph) {
        super(areaGraph);
    }

    @Override
    protected CarPathsBunch computeCarPath(Node start) throws PathsConstructionException {
        System.out.println("Dijkstra started! Start: " + start.getNodeIndex() + "\n");
        CarPathsBunch carPathsBunch = new CarPathsBunch(start);
        List<Node> unmarkedNodes = new ArrayList<>(graph.getNodesSet());
        unmarkedNodes.sort(Comparator.comparingDouble(Node::getWeight));

        start.setWeight(0);
        Node currentNode = start;

        HashMap<Node, Node> predecessors = new HashMap<>();
        for (Node node : unmarkedNodes) {
            predecessors.put(node, null);
        }

        while (!unmarkedNodes.isEmpty()) {
            unmarkedNodes.remove(currentNode);
            for (Node neighbourNode : currentNode.getOutNodes()) {
                Edge edgeToNeighbour = currentNode.getRightEdges().stream()
                        .filter(e -> e.getEnd().equals(neighbourNode))
                        .findFirst()
                        .orElse(null);
                if (neighbourNode.getWeight() > currentNode.getWeight() + edgeToNeighbour.getWeight()) {
                    if (unmarkedNodes.contains(neighbourNode)) {
                        neighbourNode.setWeight(currentNode.getWeight() + edgeToNeighbour.getWeight());
                        neighbourNode.setEdgeToPrev(edgeToNeighbour);
                        predecessors.put(neighbourNode, currentNode);
                    }
                }
            }

            try {
                currentNode = unmarkedNodes.get(0);
            } catch (IndexOutOfBoundsException exc) {

            }

            if (currentNode.getWeight() == Double.POSITIVE_INFINITY) {              //if there are only unreachable nodes left
                throw new PathsConstructionException("One or more nodes are unreachable!", unmarkedNodes);
            }
        }

        List<Node> list = new ArrayList<>(graph.getNodesSet());
        list.remove(start);
        for (Node end : list) {           //retrieve concrete paths from starting node to node
            carPathsBunch.getCarPathsByEnds().put(end, PathRetriever.retrievePath(start, end, predecessors));
        }
        return carPathsBunch;
    }

}

