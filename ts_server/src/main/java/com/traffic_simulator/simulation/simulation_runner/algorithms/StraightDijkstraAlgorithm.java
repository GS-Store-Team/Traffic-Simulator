package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.simulation.graph.GraphMap;
import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.ElementColor;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.exceptions.PathsConstructionException;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Collections.addAll;

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
    protected CarPathsBunch computeCarPath(NodeNe start) throws PathsConstructionException {
        System.out.println("Dijkstra started! Start: " + start.hashCode() + "\n");
        CarPathsBunch carPathsBunch = new CarPathsBunch(start);
        List<NodeNe> unmarkedNodes = new ArrayList<>(graph.getNodes());
        unmarkedNodes.sort(Comparator.comparingDouble(NodeNe::getWeight));

        start.setWeight(0);
        NodeNe currentNode = start;

        HashMap<NodeNe, NodeNe> predecessors = new HashMap<>();
        for (NodeNe node : unmarkedNodes) {
            predecessors.put(node, null);
        }

        //graph.getNodes().stream().forEach((NodeNe n) -> System.out.println(n.getAttachmentPoint()));

        while (!unmarkedNodes.isEmpty()) {
            unmarkedNodes.remove(currentNode);

            AttachmentPoint ref = currentNode.getAttachmentPoint();             //retrieve all roads which have a way to another nodeNe
            List<Road> refRoads = new ArrayList<>();
            refRoads.addAll(ref.getStartingRoads().stream().filter((Road r) -> !r.getRightLanes().isEmpty()).toList());
            refRoads.addAll(ref.getFinishingRoads().stream().filter((Road r) -> !r.getLeftLanes().isEmpty()).toList());

            for (NodeNe nodeNe : currentNode.getNodesList()) {
                Road road;
                try {                               //retrieve road, which is connected with current node
                    road = refRoads.stream()
                            .filter((Road r) -> ref.getStartingRoads().contains(r) & nodeNe.getAttachmentPoint().getFinishingRoads().contains(r) ||
                                    nodeNe.getAttachmentPoint().getStartingRoads().contains(r) & ref.getFinishingRoads().contains(r))
                            .min(Comparator.comparingDouble(Road::getWeight))
                            .get();
                } catch (NoSuchElementException exc) {
                    throw exc;
                }
                if (nodeNe.getWeight() > currentNode.getWeight() + road.getWeight()) {
                    if (unmarkedNodes.contains(nodeNe)) {
                        nodeNe.setWeight(currentNode.getWeight() + road.getWeight());
                        nodeNe.setRoadToPrev(road);
                        predecessors.put(nodeNe, currentNode);
                    }
                }
            }

            /*for (NodeNe node : graph.getNodes()) {
                System.out.println(node.hashCode() + " - " + node.getWeight());
            }
            System.out.println("-----");
            */
            try {
                currentNode = unmarkedNodes.stream().min(Comparator.comparingDouble(NodeNe::getWeight)).get();
            } catch (NoSuchElementException exc) {
                break;
            }
            //System.out.println("5: aaaaaaaaaaaaaaaaaaaaa");


            if (currentNode.getWeight() == Double.POSITIVE_INFINITY) {              //if there are only unreachable nodes left
                throw new PathsConstructionException("One or more nodes are unreachable!", unmarkedNodes);
            }
        }

        /*System.out.println(start.hashCode());
        for (NodeNe key : predecessors.keySet()) {
            System.out.print(key.hashCode() + " -> ");
            if (predecessors.get(key) == null) {
                System.out.println("null");
            } else {
                System.out.println(predecessors.get(key).hashCode());
            }
        }*/

        List<NodeNe> list = new ArrayList<>(graph.getNodes());
        list.remove(start);
        for (NodeNe end : list) {           //retrieve concrete paths from starting node to node
            carPathsBunch.getCarPathsEndsMap().put(end, PathRetriever.retrievePath(start, end, predecessors));
        }
        return carPathsBunch;
    }

}
