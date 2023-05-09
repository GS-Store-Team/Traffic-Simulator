package com.traffic_simulator.simulation.graph.graph_elements;

import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Node {
    private int nodeIndex;
    private AttachmentPoint attachmentPoint;
    private Set<Edge> edgesToPrev;
    private double weight = Double.POSITIVE_INFINITY;

    private List<Node> nodesList = new ArrayList<>();
    private List<Node> outNodes = new ArrayList<>();
    private List<Node> inNodes = new ArrayList<>();

    private List<Edge> rightEdges = new ArrayList<>();
    private List<Edge> leftEdges = new ArrayList<>();

    public Node(AttachmentPoint attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
        this.edgesToPrev = new HashSet<>();
    }

    public Node(Node node) {          //copying constructor
        this.nodeIndex = node.nodeIndex;
        this.attachmentPoint = node.attachmentPoint;
        this.edgesToPrev = new HashSet<>(node.edgesToPrev);
        this.weight = node.weight;
        this.nodesList = new ArrayList<>(node.nodesList);
        this.outNodes = new ArrayList<>(node.outNodes);
        this.inNodes = new ArrayList<>(node.inNodes);
        this.rightEdges = new ArrayList<>(node.rightEdges);
        this.leftEdges = new ArrayList<>(node.leftEdges);
    }

    public void addNode(Node node) {
        nodesList.add(node);
    }

    public void addOutNode(Node node) {
        nodesList.add(node);
        outNodes.add(node);
    }

    public void addInNode(Node node) {
        nodesList.add(node);
        inNodes.add(node);
    }


}