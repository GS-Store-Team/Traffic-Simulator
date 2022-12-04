package com.traffic_simulator.businnes_logic.simulation_runner;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.car.CarPath;

import java.util.List;

public class DijkstraAlgorithm extends PathfindingAlgorithm {

    public DijkstraAlgorithm(RoadMap roadMap) {
        super(roadMap);
    }
    @Override
    public List<CarPath> computeCarPath(PathfindingAlgorithm.calculationType calculationType, List<GraphObject> ends) {
        return null;
    }

}
