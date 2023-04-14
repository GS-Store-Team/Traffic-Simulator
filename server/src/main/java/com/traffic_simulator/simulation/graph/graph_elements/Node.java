package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;

import java.util.ArrayList;
import java.util.List;

import com.traffic_simulator.simulation.models.road.Road;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Node {
    private int nodeIndex;
    private List<Node> nodesList = new ArrayList<>();
    private AttachmentPoint attachmentPoint;
    private Road roadToPrev;
    private double weight = Double.POSITIVE_INFINITY;

    public Node(AttachmentPoint attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
        this.roadToPrev = null;
    }

    public Node(Node node) {          //copying constructor
        this.attachmentPoint = node.attachmentPoint;
        this.roadToPrev = node.roadToPrev;
        this.weight = node.weight;
        this.nodesList = new ArrayList<>(node.nodesList);
    }

    public void addNodeToList(Node node) {
        nodesList.add(node);
    }


}