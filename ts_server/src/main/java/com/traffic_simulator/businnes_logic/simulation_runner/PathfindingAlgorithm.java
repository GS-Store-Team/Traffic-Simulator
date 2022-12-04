package com.traffic_simulator.businnes_logic.simulation_runner;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.car.CarPath;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PathfindingAlgorithm {
    protected enum calculationType {ALL, NATURAL, TRAFFIC}
    private enum endsType {BUILDING, ATTACHMENT_POINT, ROAD, ALL}
    private ConcurrentHashMap<GraphObject, List<CarPath>> endToPathConcurrentHashMap;

    private RoadMap roadMap;
    //private GraphObject start;
    public PathfindingAlgorithm(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public List<CarPath> compute(GraphObject start, endsType endsType, calculationType calculationType) {
        List<CarPath> result = new ArrayList<>();
        List<GraphObject> ends = new ArrayList<>();

        switch (endsType) {
            case BUILDING -> ends.addAll(roadMap.getBuildings());
            case ATTACHMENT_POINT -> ends.addAll(roadMap.getAttachmentPoints());
            case ROAD -> ends.addAll(roadMap.getRoads());
            case ALL -> {
                ends.addAll(roadMap.getBuildings());
                ends.addAll(roadMap.getRoads());
                ends.addAll(roadMap.getAttachmentPoints());
            }
        }

        result = computeCarPath(calculationType, ends);
        return result;
    }
    abstract List<CarPath> computeCarPath(calculationType calculationType, List<GraphObject> ends);

}
