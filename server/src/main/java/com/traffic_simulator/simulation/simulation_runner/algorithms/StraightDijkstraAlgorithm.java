package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.simulation.graph.GraphMap;
import com.traffic_simulator.exceptions.PathsConstructionException;

import java.util.*;

//TODO Придумать в каком виде извлекать путь
public class StraightDijkstraAlgorithm extends PathFindingAlgorithm {

    /**
     * Common  <a href="https://e-maxx.ru/algo/dijkstra">Dijkstra algorithm</a>.<br>
     * Called "straight" because builds routes without loops.
     */
    public StraightDijkstraAlgorithm(GraphMap graphMap) {
        super(graphMap);
    }

    @Override
    protected CarPathsBunch computeCarPath(Node start) throws PathsConstructionException {
        System.out.println("Dijkstra started! Start: " + start.getNodeIndex() + "\n");
        CarPathsBunch carPathsBunch = new CarPathsBunch(start);
        List<Node> unmarkedNodes = new ArrayList<>(graph.getNodes());
        unmarkedNodes.sort(Comparator.comparingDouble(Node::getWeight));

        start.setWeight(0);
        Node currentNode = start;

        HashMap<Node, Node> predecessors = new HashMap<>();
        for (Node node : unmarkedNodes) {
            predecessors.put(node, null);
        }

        while (!unmarkedNodes.isEmpty()) {
            unmarkedNodes.remove(currentNode);

            AttachmentPoint ref = currentNode.getAttachmentPoint();             //retrieve all roads which have a way to another nodeNe
            List<Road> refRoads = new ArrayList<>();
            refRoads.addAll(ref.getStartingRoads().stream().filter((Road r) -> !r.getRightLanes().isEmpty()).toList());
            refRoads.addAll(ref.getFinishingRoads().stream().filter((Road r) -> !r.getLeftLanes().isEmpty()).toList());

            for (Node neighbourNode : currentNode.getNodesList()) {
                Road road;
                try {                               //retrieve road, which is connected with current neighbourNode
                    road = refRoads.stream()
                            .filter((Road r) ->
                                    ref.getStartingRoads().contains(r) & neighbourNode.getAttachmentPoint().getFinishingRoads().contains(r) ||
                                    neighbourNode.getAttachmentPoint().getStartingRoads().contains(r) & ref.getFinishingRoads().contains(r))
                            .min(Comparator.comparingDouble(Road::getWeight))
                            .get();
                } catch (NoSuchElementException exc) {
                    throw exc;
                }
                if (neighbourNode.getWeight() > currentNode.getWeight() + road.getWeight()) {
                    if (unmarkedNodes.contains(neighbourNode)) {
                        neighbourNode.setWeight(currentNode.getWeight() + road.getWeight());
                        neighbourNode.setRoadToPrev(road);
                        predecessors.put(neighbourNode, currentNode);
                    }
                }
            }

            try {
                currentNode = unmarkedNodes.stream()
                        .min(Comparator.comparingDouble(Node::getWeight))
                        .get();
            } catch (NoSuchElementException exc) {
                break;
            }

            if (currentNode.getWeight() == Double.POSITIVE_INFINITY) {              //if there are only unreachable nodes left
                throw new PathsConstructionException("One or more nodes are unreachable!", unmarkedNodes);
            }
        }

        List<Node> list = new ArrayList<>(graph.getNodes());
        list.remove(start);
        for (Node end : list) {           //retrieve concrete paths from starting node to node
            carPathsBunch.getCarPathsByEnds().put(end, PathRetriever.retrievePath(start, end, predecessors));
        }
        return carPathsBunch;
    }

}
