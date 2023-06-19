package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.car.Navigator;
import com.traffic_simulator.simulation.models.road.Lane;
import com.traffic_simulator.simulation.models.road.Road;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class Edge {
    private final Road refRoad;
    private final Lane refLane;
    private final Node start;
    private final Node end;
    private double weight;
    private final RoadSide roadSide;

    private final List<Navigator> navigators;

    public Edge(Road refRoad, Lane refLane, Node start, Node end, RoadSide roadSide) {
        this.refRoad = refRoad;
        this.refLane = refLane;
        this.start = start;
        this.end = end;
        this.roadSide = roadSide;
        this.weight = calculateWeight();
        this.navigators = new ArrayList<>();
    }

    public double calculateWeight() {
        double weight = 0;
        try {
            for (Navigator navigator : navigators) {
                weight += 1;
            }
        } catch (NullPointerException exception) {

        }

        this.weight = weight;
        return weight;
    }
}
