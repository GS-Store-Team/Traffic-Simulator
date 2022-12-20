package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import java.util.List;
import lombok.Data;

@Data
public class NodeNe {
    private List<NodeNe> nodesList;
    private AttachmentPoint attachmentPoint;

    public NodeNe(AttachmentPoint attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
    }

    public void addNodeToList(NodeNe node){
        nodesList.add(node);
    }
}