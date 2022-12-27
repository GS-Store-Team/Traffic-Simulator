package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class NodeNe {
    private List<NodeNe> nodesList = new ArrayList<>();
    private AttachmentPoint attachmentPoint;
    private double weight = Double.POSITIVE_INFINITY;

    public NodeNe(AttachmentPoint attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
    }

    public void addNodeToList(NodeNe node){
        nodesList.add(node);
    }
}