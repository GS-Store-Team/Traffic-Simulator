package com.traffic_simulator.simulation.models.road;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.models.signs.road_signs.RoadSign;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import com.traffic_simulator.simulation.models.supportive.cell.CrossroadCell;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
public class Lane  {

    private int localId;
    private List<Cell> cells;
    private List<RoadSign> roadSigns;
    private Coordinates startCoordinates;
    private Coordinates endCoordinates;

    private CrossroadCell startingCrossroadCell;
    private CrossroadCell endingCrossroadCell;
    private int counter = 0;

    public Lane(int localId, Coordinates startCoordinates, Coordinates endCoordinates) {
        this.cells = new ArrayList<>();
        this.roadSigns = new ArrayList<>();
        this.startCoordinates = startCoordinates;
        this.endCoordinates = endCoordinates;
        this.localId = localId;

        addCellsByCoordinates();
    }

    private void addCellsByCoordinates() {
        int laneDiagonal = computeDiagonal(startCoordinates, endCoordinates);
        int cellDiagonal = computeDiagonal(new Coordinates(0, 0), new Coordinates(GlobalSettings.cellWidth, GlobalSettings.cellLength));

        int cellsAmount = laneDiagonal / cellDiagonal;

        for (int i = 0; i < cellsAmount; i++) {
            cells.add(new Cell(computeOffsetCoordinates(startCoordinates, endCoordinates, cellsAmount, i)));
        }
    }

    private int computeDiagonal(Coordinates startCoordinates, Coordinates endCoordinates) {
        return (int) Math.sqrt(
                Math.pow((endCoordinates.getX() - startCoordinates.getX()), 2) +
                        Math.pow((endCoordinates.getY() - startCoordinates.getY()), 2)
        );
    }

    private Coordinates computeOffsetCoordinates(Coordinates startCoordinates, Coordinates endCoordinates, int cellsAmount, int index) {
        return new Coordinates(
                Math.sqrt(
                        Math.pow((endCoordinates.getX() - startCoordinates.getX()), 2)) / cellsAmount * index,
                Math.sqrt(
                        Math.pow((endCoordinates.getY() - startCoordinates.getY()), 2)) / cellsAmount * index
        );
    }

    public double computeTrafficWeight() {
        double weight = 0;
        for (Cell cell : cells) {
            weight += cell.getTrafficWeight();
        }
        return weight * GlobalSettings.cellTrafficWeightModifier;
    }
}