package com.traffic_simulator.businnes_logic;

import com.traffic_simulator.businnes_logic.beans.SimulationContext;
import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.attachment_point.Crossroad;
import com.traffic_simulator.businnes_logic.models.buildings.*;
import com.traffic_simulator.businnes_logic.models.buildings.types.EntertainmentBuilding;
import com.traffic_simulator.businnes_logic.models.buildings.types.LivingBuilding;
import com.traffic_simulator.businnes_logic.models.buildings.types.PedestrianArea;
import com.traffic_simulator.businnes_logic.models.buildings.types.WorkplaceBuilding;
import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.models.supportive.BuildingType;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import com.traffic_simulator.dto.MapStateDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class RoadMap {
    private final SimulationContext simulationContext;
    private String name;
    private String description;
    private List<String> authors;
    private Date creationDate;
    private Date lastEditDate;

    private Coordinates center;
    private List<AttachmentPoint> attachmentPoints;
    private List<Road> roads;
    private List<Building> buildings;

    public RoadMap(String name, String description, String author, SimulationContext simulationContext) {
        this.name = name;
        this.description = description;
        this.simulationContext = simulationContext;
        this.authors = new ArrayList<>();
        this.authors.add(author);

        this.creationDate = new Date();
        this.lastEditDate = new Date();

        this.center = new Coordinates(0, 0);
        this.attachmentPoints = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public RoadMap(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
    }

    public MapStateDTO getCurrentMapConfig() {
        MapStateDTO mapStateDTO = new MapStateDTO(simulationContext.getBuildingDTOList(),
                simulationContext.getRoadDTOList(),
                null,
                null);

        return mapStateDTO;
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
        Road road = new Road(start, end, rightLanes, leftLanes);
        boolean startExists = false;
        boolean endExists = false;

        for (AttachmentPoint point : attachmentPoints) {            //check for attachment points existence and set its start and end, if they exist
            if (point.getCoordinates().x == start.x & point.getCoordinates().y == start.y) {
                road.setStartPoint(point);
                startExists = true;
            } else if (point.getCoordinates().x == end.x & point.getCoordinates().y == end.y) {
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
     * @param upLeftCorner up left corner coordinates of building rectangle
     * @param downRightCorner down right corner coordinates of building rectangle
     * @param center center coordinates of building rectangle
     * @param type building type
     * @param parkingSize amount of cars, which can park in the building parking zone
     * @return newly created building object
     */
    public Building addBuilding(Coordinates upLeftCorner, Coordinates downRightCorner, Coordinates center, BuildingType type, int parkingSize) {
        Building building = new Building(upLeftCorner, downRightCorner, center, "name", "street", "index");
        switch (type) {
            case LIVING -> building = new LivingBuilding(upLeftCorner, downRightCorner, center, "name", "street", "index");
            case WORK -> building = new WorkplaceBuilding(upLeftCorner, downRightCorner, center, "name", "street", "index");
            case ENTERTAINMENT ->
                    building = new EntertainmentBuilding(upLeftCorner, downRightCorner, center, "name", "street", "index");
            case PEDESTRIAN_AREA ->
                    building = new PedestrianArea(upLeftCorner, downRightCorner, center, "name", "street", "index");
        }
        building.setParkingZone(new ParkingZone(parkingSize, upLeftCorner, downRightCorner));

        for (AttachmentPoint attachmentPoint : attachmentPoints) {
            if (MyVectorGeometry.pointIsInBuildingCircleArea(attachmentPoint.getCoordinates(), building)) {
                attachmentPoint.getConnectedBuildings().add(building);
                building.setConnectedPoint(attachmentPoint);
            }
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
