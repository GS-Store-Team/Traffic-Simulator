package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.MapObject;
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
    private final MapObject refMapObject;
    private final double selfWeight;
    private double weightMark;
    private ElementColor elementColor;
    private Edge pathPrevNodeEdge;
    public Node(MapObject refMapObject) {
        this.inputEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
        this.refMapObject = refMapObject;
        this.selfWeight = refMapObject.getNaturalWeight();
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
