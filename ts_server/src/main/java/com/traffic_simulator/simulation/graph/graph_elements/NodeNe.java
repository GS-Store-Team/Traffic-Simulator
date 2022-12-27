package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;

import java.util.ArrayList;
import java.util.List;

import com.traffic_simulator.simulation.models.road.Road;
import lombok.Data;

@Data
public class NodeNe {
    private List<NodeNe> nodesList = new ArrayList<>();
    private AttachmentPoint attachmentPoint;
    private Road roadToPrev;
    private double weight = Double.POSITIVE_INFINITY;

    public NodeNe(AttachmentPoint attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
        this.roadToPrev = null;
    }

    public NodeNe(NodeNe nodeNe) {          //copying constructor
        this.attachmentPoint = nodeNe.attachmentPoint;
        this.roadToPrev = nodeNe.roadToPrev;
        this.weight = nodeNe.weight;
        this.nodesList = new ArrayList<>(nodeNe.nodesList);
    }
    public void addNodeToList(NodeNe node){
        nodesList.add(node);
    }
}