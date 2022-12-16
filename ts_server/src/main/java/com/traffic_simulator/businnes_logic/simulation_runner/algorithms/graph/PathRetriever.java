package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

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
