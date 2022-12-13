package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.car.CarPath;

import java.util.HashMap;
import java.util.List;

public class DijkstraAlgorithm extends PathfindingAlgorithm {

    //TODO Придумать удобные для алгоритма структуры данных и написать алгоритм
    //private HashMap<GraphObject, Boolean>

    /**
     * Common  <a href="https://e-maxx.ru/algo/dijkstra">Dijkstra algorithm</a>
     * @param roadMap
     */
    public DijkstraAlgorithm(RoadMap roadMap) {
        super(roadMap);
    }
    @Override
    public List<CarPath> computeCarPath(PathfindingAlgorithm.calculationType calculationType, List<GraphObject> ends) {
        return null;
    }

}
