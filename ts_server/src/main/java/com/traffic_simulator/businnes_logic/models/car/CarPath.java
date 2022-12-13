package com.traffic_simulator.businnes_logic.models.car;

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
    private GraphObject startPoint;
    private GraphObject endPoint;
    private Deque<GraphObject> path;
    public CarPath(GraphObject startPoint, GraphObject endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.path = new ArrayDeque<>();
    }

    public void addGraphObject(GraphObject graphObject) {

    }

    public int calculateNaturalWeight() {
        int weight = 0;
        for (GraphObject graphObject : path) {
            weight += graphObject.getNaturalWeight();
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
