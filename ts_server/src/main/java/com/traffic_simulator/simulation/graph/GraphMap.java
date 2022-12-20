package com.traffic_simulator.simulation.graph;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.MapStateDTO;
import com.traffic_simulator.dto.PointDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.context.SimulationContext;
import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@ToString
@Getter
public class GraphMap {
    private List<Node> nodes = new ArrayList<>();
    private List<Node> crossroadNodes = new ArrayList<>();
    private List<Node> buildingNodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();


    private final SimulationContext simulationContext;
    private Validation validation;
    private List<NodeNe> nodesList;
    private List<Road> roads = new ArrayList<>();
    private List<Building> buildings = new ArrayList<>();

    //TODO Сделать свои исключения

    /**
     * Graph representation of the RoadMap.
     * Supposed to be created once for one map configuration (if it was changed you should recreate GraphMap).
     * Creates two edges (for left and right parts) for each road and connection between attachment point and building.

     */

    public GraphMap(SimulationContext simulationContext){
        this.simulationContext = simulationContext;
    }

    public void constructGraphMap() throws InvalidMapException {
        validation = new Validation(simulationContext);
        validation.checkErrors();
        constructGraph();
    }

    public Map<String, List<Long>> getErrors(){
        return validation.getErrors();
    }

    private void constructGraph(){
        Map<PointDTO, NodeNe> map = new HashMap<>();
        Set<PointDTO> points = new HashSet<>();
        for (RoadDTO roadDTO : simulationContext.getRoadDTOList()){
            points.add(roadDTO.getStart());
            points.add(roadDTO.getEnd());
        }
        nodesList = points.stream().map(p -> {
            NodeNe answer = new NodeNe(new AttachmentPoint(new Coordinates(p.getX(), p.getY())));
            map.put(p, answer);
            return answer;
        }).toList();

        for (RoadDTO roadDTO : simulationContext.getRoadDTOList()){
            NodeNe start = map.get(roadDTO.getStart());
            NodeNe end = map.get(roadDTO.getEnd());
            Road road = new Road(
                    start.getAttachmentPoint().getCoordinates(),
                    end.getAttachmentPoint().getCoordinates(),
                    roadDTO.getForwardLanesCnt(),
                    roadDTO.getReverseLanesCnt());

            roads.add(road);

            start.addNodeToList(end);
            end.addNodeToList(start);

            start.getAttachmentPoint().addStartingRoad(road);
            end.getAttachmentPoint().addFinishingRoad(road);
        }

        Map<PointDTO, PointDTO> map1 = validation.getMap();

        for (BuildingDTO buildingDTO: simulationContext.getBuildingDTOList()){
            PointDTO pointDTO = map1.get(buildingDTO.getLocation());
            NodeNe nodeNe = map.get(pointDTO);
            Building building = new Building(new Coordinates(buildingDTO.getLocation().getX(), buildingDTO.getLocation().getY()), BuildingType.LIVING);
            buildings.add(building);
            nodeNe.getAttachmentPoint().addBuilding(building);
        }
    }

    public List<NodeNe> getNodes(){
        return nodesList;
    }

    public MapStateDTO getCurrentMapConfig()    {
        return new MapStateDTO(simulationContext.getBuildingDTOList(),
                               simulationContext.getRoadDTOList(),
                          null, null);
    }
}
