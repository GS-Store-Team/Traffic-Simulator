package com.traffic_simulator.simulation.models;

import com.traffic_simulator.simulation.MyVectorGeometry;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.attachment_point.Crossroad;
import com.traffic_simulator.simulation.models.buildings.*;
import com.traffic_simulator.simulation.models.buildings.types.EntertainmentBuilding;
import com.traffic_simulator.simulation.models.buildings.types.LivingBuilding;
import com.traffic_simulator.simulation.models.buildings.types.PedestrianArea;
import com.traffic_simulator.simulation.models.buildings.types.WorkplaceBuilding;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
//import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.dto.MapStateDTO;
import com.traffic_simulator.simulation.simulation_runner.algorithms.PathFindingAlgorithm;
import com.traffic_simulator.simulation.graph.GraphMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RoadMap {
    private List<AttachmentPoint> attachmentPoints = new ArrayList<>();
    private List<Road> roads = new ArrayList<>();
    private List<Building> buildings = new ArrayList<>();

    private GraphMap graphMap;
    private PathFindingAlgorithm pathfindingAlgorithm;

    public RoadMap(GraphMap graphMap, PathFindingAlgorithm pathfindingAlgorithm)  {
        this.graphMap = graphMap;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
    }

    /**
     * Adds new attachment point, depletes road if point was placed in it (becomes crossroad)
     * and adds bidirectional connection between attachment point and nearby building (connection radius).
     * @param coordinates coordinates of the attachment point on the map
     * @return newly created attachment point object
     */
    public AttachmentPoint addAttachmentPoint(Coordinates coordinates) {
        for (Road road : roads) {
            if (MyVectorGeometry.pointIsOnRoad(coordinates, road)) {
                Crossroad crossroad = new Crossroad(coordinates);
                depleteRoadInTwo(road, crossroad);

                for (Building building : buildings) {
                    if (MyVectorGeometry.pointIsInBuildingCircleArea(coordinates, building)) {
                        crossroad.getConnectedBuildings().add(building);
                        building.setConnectedPoint(crossroad);
                    }
                }

                attachmentPoints.add(crossroad);
                return crossroad;
            }
        }

        AttachmentPoint attachmentPoint = new AttachmentPoint(coordinates);

        for (Building building : buildings) {
            if (MyVectorGeometry.pointIsInBuildingCircleArea(coordinates, building)) {
                attachmentPoint.getConnectedBuildings().add(building);
                building.setConnectedPoint(attachmentPoint);
            }
        }

        attachmentPoints.add(attachmentPoint);
        return attachmentPoint;
    }

    /**
     * Depletes one road to two, connects them with newly created crossroad and deletes initial road.
     * @param road road to deplete
     * @param crossroad crossroad, which was placed in the middle of the road
     */
    private void depleteRoadInTwo(Road road, Crossroad crossroad) {     //to-do save signs on appropriate sides
        Road road1 = new Road(road.getStartPoint().getCoordinates(),
                crossroad.getCoordinates(),
                road.getRightLanes().size(),
                road.getLeftLanes().size());
        road1.setStartPoint(road.getStartPoint());
        road1.setEndPoint(crossroad);

        Road road2 = new Road(crossroad.getCoordinates(),
                road.getEndPoint().getCoordinates(),
                road.getRightLanes().size(),
                road.getLeftLanes().size());
        road2.setStartPoint(crossroad);
        road2.setEndPoint(road.getEndPoint());


        crossroad.addRoad(road1);
        crossroad.addRoad(road2);

        roads.remove(road);
    }

    /**
     * Adds new road to map. It considers 3 scenarios: <br>
     * 1) road is completely new and not connected to AttachmentPoints. <br>
     * 2) road's one or more attachment points are already exist. <br>
     * 3) road's one or more attachment points are placed in the middle of the other road. <br>
     * @param start starting attachment point
     * @param end ending attachment point
     * @param rightLanes amount of right lanes (forward direction)
     * @param leftLanes amount of left lanes (backward direction)
     * @return newly created road object
     */
    public Road addRoad(Coordinates start, Coordinates end, int rightLanes, int leftLanes) {
        Road road = new Road(new AttachmentPoint(start), new AttachmentPoint(end), rightLanes, leftLanes);
        boolean startExists = false;
        boolean endExists = false;

        for (AttachmentPoint point : attachmentPoints) {            //check for attachment points existence and set its start and end, if they exist
            if (point.getCoordinates().getX() == start.getX() & point.getCoordinates().getY() == start.getY()) {
                road.setStartPoint(point);
                startExists = true;
            } else if (point.getCoordinates().getX() == end.getX() & point.getCoordinates().getY() == end.getY()) {
                road.setEndPoint(point);
                endExists = true;
            }
        }

        if (!startExists) {                                             //create new attachment points if they are not existent yet
            AttachmentPoint startingPoint = addAttachmentPoint(start);
            road.setStartPoint(startingPoint);
        }
        if (!endExists) {
            AttachmentPoint endingPoint = addAttachmentPoint(end);
            road.setEndPoint(endingPoint);
        }

        roads.add(road);
        return road;
    }

    /**
     * Add building of the specified type with parking zone.
     * Connects it to the last added attachment point, if it is located in building connection radius.
     * @return newly created building object
     */
    public Building getAllBuildings(GraphMap map) {
        Building building = null;
        for (Building build: map.getBuildings()) {
            building = new Building(build.getUpLeftCorner(),  "name", "street", "index", build.getType());
            switch (build.getType()) {
                case LIVING -> building = new LivingBuilding(build.getUpLeftCorner(), "name", "street", "index",
                        BuildingType.LIVING);
                case WORK -> building = new WorkplaceBuilding(build.getUpLeftCorner(),  "name", "street", "index",
                        BuildingType.WORK);
                case ENTERTAINMENT ->
                        building = new EntertainmentBuilding(build.getUpLeftCorner(),  "name", "street", "index",
                                BuildingType.ENTERTAINMENT);
                case PEDESTRIAN_AREA ->
                        building = new PedestrianArea(build.getUpLeftCorner(),  "name", "street", "index",
                                BuildingType.PEDESTRIAN_AREA);
            }
            //building.setParkingZone(new ParkingZone(parkingSize, upLeftCorner));
        }


        return building;
    }

    public void removeAttachmentPoint(AttachmentPoint aPoint) {
    }

    public void removeRoad() {

    }

    public void removeBuilding() {

    }
}
