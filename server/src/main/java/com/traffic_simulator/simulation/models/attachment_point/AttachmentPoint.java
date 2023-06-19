package com.traffic_simulator.simulation.models.attachment_point;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.MyVectorGeometry;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.road.Lane;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.simulation.models.supportive.cell.CrossroadCell;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class AttachmentPoint extends MapObject {
    private Coordinates coordinates;
    private List<Building> connectedBuildings;
    private List<Road> startingRoads;
    private List<Road> finishingRoads;
    private List<Road> allRoads;

    private Road referenceRoad;
    private Map<Road, Double> roadsByAngle;
    private CrossroadCell[][] CrossroadCellMatrix;
    private int xSide = 0;
    private int ySide = 0;

    public AttachmentPoint(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.connectedBuildings = new ArrayList<>();
        this.startingRoads = new ArrayList<>();
        this.finishingRoads = new ArrayList<>();
        this.allRoads = new ArrayList<>();
        this.CrossroadCellMatrix = new CrossroadCell[0][0];
        this.roadsByAngle = new HashMap<>();
    }

    @Override
    public Map<Integer, Double> getTrafficWeight() {
        HashMap<Integer, Double> hashMap = new HashMap<>();
        hashMap.put(0, 1 * GlobalSettings.cellTrafficWeightModifier);
        return hashMap;
    }

    public void addBuilding(Building building) {
        connectedBuildings.add(building);
    }

    public void addStartingRoad(Road road) {
        startingRoads.add(road);
        allRoads.add(road);

        //orderRoad(road);
        //configureCrossroad();
    }

    public void addFinishingRoad(Road road) {
        finishingRoads.add(road);
        allRoads.add(road);

        //orderRoad(road);
        //configureCrossroad();
    }

    private void configureCrossroad() {
        if (allRoads.size() <= 1) {
            return;
        }

        configureCrossroadCellMatrix();
    }

    private void orderRoad(Road road) {
        if (allRoads.size() == 1) {
            referenceRoad = road;
            roadsByAngle.put(road, 0d);
            return;
        }

        double angle = Math.toDegrees(
                Math.acos(
                        MyVectorGeometry.computeRoadsAngleCos(referenceRoad, road)));
        if (roadsByAngle.containsValue(angle)) {
            roadsByAngle.put(road, 360 - angle);
        }
        roadsByAngle.put(road, angle);
    }

    private void configureCrossroadCellMatrix() {
        List<Integer> sortedList = new ArrayList<>(
                allRoads.stream()
                        .map(r -> r.getRightLanes().size() + r.getLeftLanes().size())
                        .toList());
        sortedList.sort(Integer::compareTo);

        xSide = sortedList.get(sortedList.size() - 1);
        ySide = sortedList.get(sortedList.size() - 2);

        CrossroadCellMatrix = new CrossroadCell[xSide][ySide];
        for (int i = 0; i < xSide; i++) {
            //change this for better positioning
            for (int j = 0; j < ySide; j++) {
                CrossroadCellMatrix[i][j] =
                        new CrossroadCell(
                                new Coordinates(
                                        coordinates.getX() + (double) (j - ySide / 2) * GlobalSettings.cellWidth,
                                        coordinates.getY() + (double) (i - xSide / 2) * GlobalSettings.cellWidth),
                                j,
                                i);
            }
        }

        for (int i = 0; i < xSide; i++) {
            for (int j = 0; j < ySide; j++) {
                if (i > 0) {
                    CrossroadCellMatrix[i][j].setUpCell(CrossroadCellMatrix[i - 1][j]);
                }
                if (i < xSide - 1) {
                    CrossroadCellMatrix[i + 1][j].setDownCell(CrossroadCellMatrix[i + 1][j]);
                }
                if (j > 0) {
                    CrossroadCellMatrix[i][j].setLeftCell(CrossroadCellMatrix[i][j - 1]);
                }
                if (j < ySide - 1) {
                    CrossroadCellMatrix[i][j].setRightCell(CrossroadCellMatrix[i][j + 1]);
                }
            }
        }

        configureCrossroadCells();
    }

    private void configureCrossroadCells() {
        for (Road road : allRoads) {
            connectRoadLanesToCrossroadCells(road);
        }
    }

    private void connectRoadLanesToCrossroadCells(Road road) {
        Coordinates roadVectorCoordinates = MyVectorGeometry.computeRoadVectorCoordinates(road);

        int cnt = 0;
        if (roadVectorCoordinates.getX() < 0) {        //from right to left
            for (Lane lane : road.getRightLanes()) {
                lane.setEndingCrossroadCell(CrossroadCellMatrix[cnt++][ySide - 1]);
            }
            for (Lane lane : road.getLeftLanes()) {
                lane.setStartingCrossroadCell(CrossroadCellMatrix[cnt++][ySide - 1]);
            }
        } else if (roadVectorCoordinates.getX() > 0) {     //from left to right
            for (Lane lane : road.getLeftLanes()) {
                lane.setStartingCrossroadCell(CrossroadCellMatrix[cnt++][ySide - 1]);
            }
            for (Lane lane : road.getRightLanes()) {
                lane.setEndingCrossroadCell(CrossroadCellMatrix[cnt++][ySide - 1]);
            }
        } else if (roadVectorCoordinates.getY() < 0) {     //from down to up
            for (Lane lane : road.getLeftLanes()) {
                lane.setStartingCrossroadCell(CrossroadCellMatrix[xSide - 1][cnt++]);
            }
            for (Lane lane : road.getRightLanes()) {
                lane.setEndingCrossroadCell(CrossroadCellMatrix[xSide - 1][cnt++]);
            }
        } else if (roadVectorCoordinates.getY() > 0) {     //from up to down
            for (Lane lane : road.getRightLanes()) {
                lane.setEndingCrossroadCell(CrossroadCellMatrix[xSide - 1][cnt++]);
            }
            for (Lane lane : road.getLeftLanes()) {
                lane.setStartingCrossroadCell(CrossroadCellMatrix[xSide - 1][cnt++]);
            }
        }
    }

    @Override
    public String toString() {
        return startingRoads.size() + " " + finishingRoads.size();
    }
}
