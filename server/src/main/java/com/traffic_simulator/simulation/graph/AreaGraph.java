package com.traffic_simulator.simulation.graph;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.PointDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.simulation.context.AreaSimulationContext;
import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.RoadSide;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.buildings.ParkingZone;
import com.traffic_simulator.simulation.models.buildings.types.LivingBuilding;
import com.traffic_simulator.simulation.models.buildings.types.WorkplaceBuilding;
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
    private final AreaSimulationContext areaSimulationContext;
    private Validation validation;
    private Set<Node> nodesSet = new HashSet<>();
    private List<Road> roads = new ArrayList<>();
    private List<Building> buildings = new ArrayList<>();
    private List<LivingBuilding> livingBuildings = new ArrayList<>();
    private List<WorkplaceBuilding> workplaceBuildings = new ArrayList<>();

    //TODO Сделать свои исключения

    /**
     * Graph representation of the RoadMap.
     * Supposed to be created once for one map configuration (if it was changed you should recreate GraphMap).
     * Creates two edges (for left and right parts) for each road and connection between attachment point and building.
     **/

    public AreaGraph(AreaSimulationContext areaSimulationContext) {
        this.areaSimulationContext = areaSimulationContext;
    }

    public void constructGraphMap() throws InvalidMapException {
        validation = new Validation(areaSimulationContext);
        validation.checkErrors();
        constructGraph();
    }

    public Map<String, List<Long>> getErrors() {
        return validation.getErrors();
    }

    private void constructGraph() {
        Map<PointDTO, Node> map = new HashMap<>();
        Set<PointDTO> points = new HashSet<>();
        for (RoadDTO roadDTO : areaSimulationContext.getRoadDTOList()) {
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

        for (RoadDTO roadDTO : areaSimulationContext.getRoadDTOList()) {
            Node start = map.get(roadDTO.start());          //may equality check needed
            Node end = map.get(roadDTO.end());
            Road road = new Road(
                    start.getAttachmentPoint().getCoordinates(),
                    end.getAttachmentPoint().getCoordinates(),
                    roadDTO.forwardLanes(),
                    roadDTO.reverseLanes());

            roads.add(road);

            start.addNodeToList(end);
            end.addNodeToList(start);

            start.getAttachmentPoint().addStartingRoad(road);
            end.getAttachmentPoint().addFinishingRoad(road);

            if (!road.getRightLanes().isEmpty()) {
                edges.add(new Edge(road, start, end, RoadSide.RIGHT));
            }
            if (!road.getLeftLanes().isEmpty()) {
                edges.add(new Edge(road, end, start, RoadSide.LEFT));
            }
        }

        Map<PointDTO, PointDTO> map1 = validation.getMap();

        for (BuildingDTO buildingDTO : areaSimulationContext.getBuildingDTOList()) {
            PointDTO pointDTO = map1.get(buildingDTO.location());
            Node node = map.get(pointDTO);
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
            building.setParkingZone(new ParkingZone(buildingDTO.parking().capacity(), SimulationUtils.pointToCoordinates(buildingDTO.location())));
            buildings.add(building);
            node.getAttachmentPoint().addBuilding(building);
        }
    }

    public void resetWeights() {
        for (Node node : nodesSet) {
            node.setWeight(Double.POSITIVE_INFINITY);
        }
    }
}

