package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.ElementColor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
public class Node {

    private List<Edge> inputEdges;
    private List<Edge> outEdges;
    private final GraphObject refGraphObject;
    private final double selfWeight;
    private double weightMark;
    private ElementColor elementColor;
    private Edge pathPrevNodeEdge;
    public Node(GraphObject refGraphObject) {
        this.inputEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
        this.refGraphObject = refGraphObject;
        this.selfWeight = refGraphObject.getNaturalWeight();
        this.weightMark = Double.POSITIVE_INFINITY;
        this.elementColor = ElementColor.WHITE;
        this.pathPrevNodeEdge = null;
    }

    public void resetMarks() {
        weightMark = Double.POSITIVE_INFINITY;
        elementColor = ElementColor.WHITE;
        pathPrevNodeEdge = null;
    }
}
