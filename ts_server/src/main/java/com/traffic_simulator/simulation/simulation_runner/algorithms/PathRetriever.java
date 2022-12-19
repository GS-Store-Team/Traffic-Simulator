package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPath;
import com.traffic_simulator.simulation.graph.graph_elements.Node;

public class PathRetriever {
    public static CarPath retrievePath(Node start, Node end) {
        CarPath carPath = new CarPath(start, end);
        Node currentNode = end;
        while (currentNode != start) {
            carPath.getNodes().addFirst(currentNode);

            carPath.getEdges().addFirst(currentNode.getPathPrevNodeEdge());     //Возможно, надо добавить исклчение на PathPrevNodeEdge = null
            currentNode = currentNode.getPathPrevNodeEdge().getStart();
        }
        carPath.getNodes().addFirst(start);
        return carPath;
    }
}
