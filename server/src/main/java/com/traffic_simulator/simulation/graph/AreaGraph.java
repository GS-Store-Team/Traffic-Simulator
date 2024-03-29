package com.traffic_simulator.simulation.graph;

import com.traffic_simulator.dto.*;
import com.traffic_simulator.models.Area;
import com.traffic_simulator.models.AreaVersion;
import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.RoadSide;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.buildings.ParkingZone;
import com.traffic_simulator.simulation.models.buildings.types.LivingBuilding;
import com.traffic_simulator.simulation.models.buildings.types.WorkplaceBuilding;
import com.traffic_simulator.simulation.models.road.Lane;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.utils.SimulationUtils;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@ToString
@Getter
public class AreaGraph {
    private List<Edge> edges = new ArrayList<>();
    private AreaDTO areaDTO;
    private AreaVersion areaVersion;
    private AreaVersionDTO areaVersionDTO;
    private VersionValidation validation;
    private Set<Node> nodesSet = new HashSet<>();
    private List<Road> roads = new ArrayList<>();
    private List<Building> buildings = new ArrayList<>();
    private List<LivingBuilding> livingBuildings = new ArrayList<>();
    private List<WorkplaceBuilding> workplaceBuildings = new ArrayList<>();

    private List<Long> buildingErrors;
    private List<Long> roadErrors;

    //TODO Сделать свои исключения

    /**
     * Graph representation of the RoadMap.
     * Supposed to be created once for one map configuration (if it was changed you should recreate GraphMap).
     * Creates two edges (for left and right parts) for each road and connection between attachment point and building.
     **/

    public AreaGraph(AreaDTO areaDTO, Long versionId) {
        this.areaDTO = areaDTO;
        this.areaVersionDTO = areaDTO.versions().stream()
                .filter(
                        v -> v.id().equals(versionId))
                .findFirst()
                .orElseThrow();
    }

    public AreaGraph(Area areaVersion, Long versionId) {
        this.areaVersion = areaVersion.getVersions().stream()
                .filter(
                        v -> v.getId().equals(versionId))
                .findFirst()
                .orElseThrow();
    }
    public void constructGraphMap() {
        validation = new VersionValidation(areaVersionDTO);
        buildingErrors = validation.getBuildingsErrorId();
        roadErrors = validation.getRoadsErrorId();

        constructGraph();
    }

    public Map<String, List<Long>> getErrors() {
        Map<String, List<Long>> errors = new HashMap<>();
        errors.put("badRoads", roadErrors);
        errors.put("badBuildings", buildingErrors);

        return errors;
    }

    private void constructGraph() {
        Map<PointDTO, Node> map = new HashMap<>();
        Set<PointDTO> points = new HashSet<>();
        for (RoadDTO roadDTO : areaVersionDTO.roads()) {
            points.add(roadDTO.start());
            points.add(roadDTO.end());
        }

        int nodeIndex = 0;
        for (PointDTO point : points) {
            Node node = new Node(new AttachmentPoint(new Coordinates(point.x(), point.y())));
            node.setNodeIndex(nodeIndex++);
            map.put(point, node);
            nodesSet.add(node);
        }

        for (RoadDTO roadDTO : areaVersionDTO.roads()) {
            Node start = map.get(roadDTO.start());          //may equality check needed
            Node end = map.get(roadDTO.end());
            Road road = new Road(
                    start.getAttachmentPoint().getCoordinates(),
                    end.getAttachmentPoint().getCoordinates(),
                    Math.toIntExact(roadDTO.forward()),
                    Math.toIntExact(roadDTO.reverse()));

            roads.add(road);

            start.addNode(end);
            end.addNode(start);

            start.getAttachmentPoint().addStartingRoad(road);
            end.getAttachmentPoint().addFinishingRoad(road);

            for (Lane lane : road.getRightLanes()) {
                edges.add(new Edge(road, lane, start, end, RoadSide.RIGHT));
                start.addOutNode(end);
            }
            for (Lane lane : road.getLeftLanes()) {
                edges.add(new Edge(road, lane, end, start, RoadSide.LEFT));
                start.addInNode(end);
            }
        }

        for (BuildingDTO buildingDTO : areaVersionDTO.buildings()) {
            Node node = new Node(
                    new AttachmentPoint(
                            new Coordinates(
                                    buildingDTO.location().x(),
                                    buildingDTO.location().y())));
            Building building;
            switch (buildingDTO.type()) {
                case LIVING -> {
                    LivingBuilding livingBuilding = new LivingBuilding(0, new Coordinates(buildingDTO.location().x(), buildingDTO.location().y()), buildingDTO.type());
                    livingBuilding.setResidingCars(buildingDTO.residents());
                    livingBuildings.add(livingBuilding);
                    building = livingBuilding;
                }
                case WORKING -> {
                    WorkplaceBuilding workplaceBuilding = new WorkplaceBuilding(0, new Coordinates(buildingDTO.location().x(), buildingDTO.location().y()), buildingDTO.type());
                    workplaceBuildings.add(workplaceBuilding);
                    building = workplaceBuilding;
                }
                default ->
                        building = new Building(0, new Coordinates(buildingDTO.location().x(), buildingDTO.location().y()), buildingDTO.type());
            }
            building.setParkingZone(new ParkingZone(Math.toIntExact(buildingDTO.parking().capacity()), SimulationUtils.pointToCoordinates(buildingDTO.location())));
            buildings.add(building);
            node.getAttachmentPoint().addBuilding(building);
        }
    }

    public void resetWeights() {
        for (Node node : nodesSet) {
            node.setWeight(Double.POSITIVE_INFINITY);
        }
    }

    public void update() {
        updateEdges();
        updateNodes();
    }

    private void updateEdges() {
        for (Edge edge : edges) {
            edge.calculateWeight();
        }
    }

    private void updateNodes() {
        for (Node node : nodesSet) {
            node.calculateWeight();
        }
    }
}

