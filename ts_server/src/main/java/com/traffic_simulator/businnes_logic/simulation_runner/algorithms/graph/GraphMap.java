package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import com.traffic_simulator.businnes_logic.RoadMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class GraphMap {
    private List<Node> nodes;
    private List<Edge> edges;

    private RoadMap roadMap;

    public GraphMap(RoadMap roadMap) {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.roadMap = roadMap;
    }

    private void translateMapToGraph() {

    }
}
