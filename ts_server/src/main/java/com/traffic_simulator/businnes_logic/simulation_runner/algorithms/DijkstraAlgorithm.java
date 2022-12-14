package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.CarPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DijkstraAlgorithm extends PathfindingAlgorithm {

    private List<GraphObject> markedNodes;
    private List<GraphObject> unmarkedNodes;
    private HashMap<GraphObject, Double> pathLengths;
    private HashMap<GraphObject, GraphObject> predecessorsList;


    /**
     * Common  <a href="https://e-maxx.ru/algo/dijkstra">Dijkstra algorithm</a>
     * @param roadMap
     */
    public DijkstraAlgorithm(RoadMap roadMap) {
        super(roadMap);
    }
    @Override
    protected List<CarPath> computeCarPath(PathfindingAlgorithm.calculationType calculationType, GraphObject start) {
        markedNodes = new ArrayList<>();

        unmarkedNodes = new ArrayList<>();
        unmarkedNodes.addAll(roadMap.getAttachmentPoints());
        unmarkedNodes.addAll(roadMap.getBuildings());
        return null;
    }

}
