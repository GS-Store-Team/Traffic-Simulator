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

    private class PathElement {
        public int side;
        public GraphObject graphObject;

        public PathElement(int side, GraphObject graphObject) {
            this.side = side;
            this.graphObject = graphObject;
        }
    }
    private GraphObject startPoint;
    private GraphObject endPoint;
    private Deque<PathElement> path;

    public CarPath(GraphObject startPoint, GraphObject endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.path = new ArrayDeque<>();
    }

    public void addGraphObject(GraphObject graphObject) {
        PathElement pathElement;

        if (graphObject.getClass().equals(AttachmentPoint.class)) {
            pathElement = new PathElement(0, graphObject);
        } else if (graphObject.getClass().equals(Building.class)) {
            pathElement = new PathElement(0, graphObject);
        } else if (graphObject.getClass().equals(Road.class)) {
            if (((Road)graphObject).)
            pathElement = new PathElement(0, graphObject);

        }
        path.addLast(graphObject);
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
            weight += graphObject.getTrafficWeight();
        }

        return weight;
    }

    public int calculateOverallWeight() {

        return calculateNaturalWeight() + calculateTrafficWeight();
    }
}
