package com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.car_path.CarPath;

import java.util.HashMap;

public class PathRetriever {
    public static CarPath retrievePath(Node start, Node end, HashMap<Node, Node> hm) {
        CarPath carPath = new CarPath(start, end);
        Node currentNode = end;
        while (currentNode != start) {
            carPath.addNode(currentNode);

            if (currentNode.getEdgesToPrev() == null) {
                System.out.println("No road to previous node in computation!");
            }
            carPath.getEdges().addFirst(currentNode.getEdgesToPrev().stream().toList());
            currentNode = hm.get(currentNode);
        }
        carPath.getNodes().addFirst(start);

        return carPath;
    }
}
