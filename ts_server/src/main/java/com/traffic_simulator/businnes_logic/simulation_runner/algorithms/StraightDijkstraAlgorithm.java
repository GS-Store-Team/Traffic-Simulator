package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.CarPathsBunch;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.Edge;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.Node;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.PathRetriever;

import java.util.Comparator;
import java.util.List;
//TODO Придумать в каком виде извлекать путь
public class StraightDijkstraAlgorithm extends PathfindingAlgorithm {

    /**
     * Common  <a href="https://e-maxx.ru/algo/dijkstra">Dijkstra algorithm</a>.<br>
     * Called "straight" because builds routes without loops.
     * @param roadMap
     */
    public StraightDijkstraAlgorithm(RoadMap roadMap) {
        super(roadMap);
    }
    @Override
    protected CarPathsBunch computeCarPath(Node start) {
        CarPathsBunch carPathsBunch = new CarPathsBunch(start);
        List<Node> unmarkedNodes = graph.getNodes();
        Node currentNode = start;

        while (!unmarkedNodes.isEmpty()) {
            unmarkedNodes.remove(currentNode);
            currentNode.getOutEdges().sort(Comparator.comparingDouble(Edge::getWeight));                //lightest nodes first
            for (Edge edge : currentNode.getOutEdges()) {
                if (edge.getEnd().getWeightMark() < currentNode.getWeightMark() + currentNode.getSelfWeight()) {       //relaxation
                    edge.getEnd().setWeightMark(currentNode.getWeightMark() + currentNode.getSelfWeight());
                    edge.getEnd().setPathPrevNodeEdge(edge);
                }
            }
            currentNode.setElementColor(ElementColor.BLACK);
            currentNode = unmarkedNodes.get(0);
            if (currentNode.getWeightMark() == Double.POSITIVE_INFINITY) {              //if there are only unreachable nodes left
                //TODO Написать исключение для недостижимой вершины в графе -> невалидный граф -> невалидная карта -> корректная симуляция невозможна
            }
        }

        for (Node end : graph.getNodes()) {
            carPathsBunch.getCarPathsEndsMap().put(end, PathRetriever.retrievePath(start, end));
        }
        return carPathsBunch;
    }

}
