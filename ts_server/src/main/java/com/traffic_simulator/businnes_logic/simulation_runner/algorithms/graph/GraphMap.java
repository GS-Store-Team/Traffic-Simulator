package com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph;

import com.traffic_simulator.businnes_logic.beans.SimulationContext;
import com.traffic_simulator.businnes_logic.models.RoadMap;
import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.attachment_point.Crossroad;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.PathfindingAlgorithm;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.Edge;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.Node;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.RoadSide;
import com.traffic_simulator.exceptions.GraphConstructionException;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class GraphMap {
    private final List<Node> nodes;
    private List<Node> crossroadNodes;
    private List<Node> buildingNodes;
    private final List<Edge> edges;
    private final SimulationContext simulationContext;

    //TODO Сделать свои исключения

    /**
     * Graph representation of the RoadMap.
     * Supposed to be created once for one map configuration (if it was changed you should recreate GraphMap).
     * Creates two edges (for left and right parts) for each road and connection between attachment point and building.
     * @param roadMap roadMap to represent as graph
     */
    public GraphMap(SimulationContext simulationContext, PathfindingAlgorithm algorithm) throws GraphConstructionException {
        this.simulationContext = simulationContext;
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        constructGraphMap();
    }

    public void resetMarks() {
        for (Node node : nodes) {
            node.resetMarks();
        }
    }

    private void constructGraphMap() throws GraphConstructionException {
        try {
            constructAttachmentPointNodes();
            connectBuildings();
            constructRoads();
        } catch (IndexOutOfBoundsException exc) {
            throw new GraphConstructionException(exc.getMessage(), null);
        }
    }

    private void constructAttachmentPointNodes() {
        crossroadNodes = new ArrayList<>();
        for (AttachmentPoint attachmentPoint : roadMap.getAttachmentPoints()) {
            Node node = new Node(attachmentPoint);
            if (attachmentPoint.getClass().equals(Crossroad.class)) {
                crossroadNodes.add(node);
            }
            nodes.add(node);
        }
    }

    private void connectBuildings() {
        buildingNodes = new ArrayList<>();

        for (Building building : roadMap.getBuildings()) {
            Node buildingNode = new Node(building);
            Node connectedAttachmentPoint;
            try {
                connectedAttachmentPoint = nodes.stream()
                        .filter((Node node) -> node.getRefMapObject().equals(building.getConnectedPoint()))
                        .toList()
                        .get(0);
            } catch (IndexOutOfBoundsException exc) {
                throw new IndexOutOfBoundsException("Node with connection to the building was not found!");
            }

            buildingNodes.add(buildingNode);
            nodes.add(buildingNode);

            edges.add(new Edge(null, connectedAttachmentPoint, buildingNode, RoadSide.RIGHT));
            edges.add(new Edge(null, buildingNode, connectedAttachmentPoint, RoadSide.LEFT));
        }
    }

    private void constructRoads() {
        for (Road road : roadMap.getRoads()) {
            Node startNode;
            Node endNode;
            try {
                startNode = crossroadNodes.stream()
                        .filter((Node node) -> node.getRefMapObject().equals(road.getStartPoint()))
                        .toList()
                        .get(0);
            } catch (IndexOutOfBoundsException exc) {
                throw new IndexOutOfBoundsException("Starting node matching road start point was not found!");
            }

            try {
                endNode = crossroadNodes.stream()
                        .filter((Node node) -> node.getRefMapObject().equals(road.getEndPoint()))
                        .toList()
                        .get(0);
            } catch (IndexOutOfBoundsException exc) {
                throw new IndexOutOfBoundsException("Starting node matching road start point was not found!");
            }

            if (!road.getRightLanes().isEmpty()) {
                Edge rightEdge = new Edge(road, startNode, endNode, RoadSide.RIGHT);
                edges.add(rightEdge);
            }
            if (!road.getLeftLanes().isEmpty()) {
                Edge leftEdge = new Edge(road, endNode, startNode, RoadSide.LEFT);
                edges.add(leftEdge);
            }
        }
    }
}
