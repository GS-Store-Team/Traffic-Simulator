package com.traffic_simulator.simulation.models.attachment_point;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.MyVectorGeometry;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.signs.crossroad_signs.CrossroadSign;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.simulation.models.supportive.cell.CellState;
import com.traffic_simulator.simulation.models.supportive.cell.CrossroadCell;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class Crossroad extends AttachmentPoint {
    private List<List<CrossroadCell>> cellsListMatrix;
    @Setter
    private List<CrossroadSign> roadSigns;

    public Crossroad(Coordinates coordinates) {
        super(coordinates);
        cellsListMatrix = new ArrayList<>();
        roads = new ArrayList<>();
        roadSigns = new ArrayList<>();
    }

    /**
     * Generate crossroad cells matrix with sizes equal to the widths of perpendicular roads,
     * where width is the amount of lanes in road.
     */
    private void generateSimpleCrossroadField() {
        List<Road> roadsSortedByLanes = roads.stream()                  //sort roads by lanes amount
                .sorted(Comparator.comparingInt(r -> r.getRightLanes().size() + r.getLeftLanes().size()))
                .toList();
        Road widestRoad1 = roadsSortedByLanes.get(0);
        Road widestRoad2 = roadsSortedByLanes.get(0);

        for (int i = 0; i < roadsSortedByLanes.size(); i++) {           //get two widest roads, which are close to be perpendicular

            double angleCos = MyVectorGeometry.computeRoadsAngleCos(widestRoad1, roads.get(i));

            if (angleCos > -0.3 & angleCos < 0.3) {
                if (roadsSortedByLanes.get(i).getRightLanes().size() + roadsSortedByLanes.get(i).getLeftLanes().size() >
                        widestRoad2.getRightLanes().size() + widestRoad2.getLeftLanes().size()) {
                    widestRoad2 = roadsSortedByLanes.get(i);
                }
            }
        }

        cellsListMatrix = new ArrayList<>();                             //create empty CrossroadCell matrix with sizes equal to the widths of perpendicular roads

        for (int i = 0; i < widestRoad1.getRightLanes().size() + widestRoad1.getLeftLanes().size(); i++) {          //fill the matrix
            cellsListMatrix.add(new ArrayList<>());
            for (int j = 0; j < widestRoad2.getRightLanes().size() + widestRoad2.getLeftLanes().size(); j++) {
                cellsListMatrix.get(i).add(new CrossroadCell(new Coordinates(i, j)));
            }
        }
    }

    public void addRoad(Road road) {
        roads.add(road);
        generateSimpleCrossroadField();
    }

    public void removeRoad(Road road) {
        roads.remove(road);
        generateSimpleCrossroadField();
    }

    /**
     * Calculate traffic weight.
     *
     * @return traffic weight distinguished to lane packs (if it is road).
     */
    @Override
    public Map<Integer, Double> getTrafficWeight() {
        HashMap<Integer, Double> hashMap = new HashMap<>();
        int occupiedCellsAmount = 0;
        for (List<CrossroadCell> list : cellsListMatrix) {
            occupiedCellsAmount += list.stream().filter((Cell c) -> c.getOccupation() == CellState.OCCUPIED).count();
        }
        hashMap.put(0, occupiedCellsAmount * GlobalSettings.cellTrafficWeightModifier);
        return hashMap;
    }
}
