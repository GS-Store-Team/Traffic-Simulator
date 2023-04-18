package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.road.Road;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Edge {
    private final Road refRoad;
    private final Node start;
    private final Node end;
    private double weight;
    private final RoadSide roadSide;

    public Edge(Road refRoad, Node start, Node end, RoadSide roadSide) {
        this.refRoad = refRoad;
        this.start = start;
        this.end = end;
        this.roadSide = roadSide;

        switch (this.roadSide) {
            case LEFT -> this.weight = this.refRoad.getNaturalWeight() + this.refRoad.computeLeftTrafficWeight();
            case RIGHT -> this.weight = this.refRoad.getNaturalWeight() + this.refRoad.computeRightTrafficWeight();
            case CLOSED -> this.weight = Double.POSITIVE_INFINITY;
            //case BUILDING_CONNECTOR -> this.weight = 0;
        }
    }
}
