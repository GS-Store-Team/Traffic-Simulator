package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.road.Road;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@Setter
@ToString
public class CarPath {
    private GraphObject graphObject;
    private CarPath prevPointInPathCarPath;
    public CarPath(GraphObject graphObject) {
        this.graphObject = graphObject;
        this.prevPointInPathCarPath = null;
    }

    public int calculateNaturalWeight() {
        int weight = 0;
        CarPath prevPoint = prevPointInPathCarPath;
        while (prevPoint != null) {
            weight += prevPoint.graphObject.getNaturalWeight();
            prevPoint = prevPoint.prevPointInPathCarPath;
        }
        return weight;
    }

    public int calculateTrafficWeight() {
        int weight = 0;
        for (GraphObject graphObject : path) {
            if (graphObject.getClass().equals(Road.class)) {
                //weight
            } else {
                weight += graphObject.getTrafficWeight().get(0);
            }
        }

        return weight;
    }

    public int calculateOverallWeight() {

        return calculateNaturalWeight() + calculateTrafficWeight();
    }
}
