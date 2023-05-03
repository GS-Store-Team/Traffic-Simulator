package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPath;

import java.util.HashMap;

public class PathRetriever {
    public static CarPath retrievePath(Node start, Node end, HashMap<Node, Node> hm) {
        CarPath carPath = new CarPath(start, end);
        Node currentNode = end;
        while (currentNode != start) {
            carPath.addNode(currentNode);

            if (currentNode.getRoadToPrev() == null) {
                System.out.println("No road to previous node in computation!");
            }
            carPath.getRoads().addFirst(currentNode.getRoadToPrev());
            currentNode = hm.get(currentNode);
        }
        carPath.getNodes().addFirst(start);

        /*for (NodeNe node : carPath.getNodes()) {
            System.out.print(node.hashCode() + " => ");
        }
        System.out.println();*/
        return carPath;
    }
}
