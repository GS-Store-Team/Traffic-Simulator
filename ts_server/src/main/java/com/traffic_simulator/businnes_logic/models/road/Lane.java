package com.traffic_simulator.businnes_logic.models.road;

import com.traffic_simulator.businnes_logic.models.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.signs.road_signs.RoadSign;
import com.traffic_simulator.businnes_logic.models.supportive.cell.Cell;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class Lane {
    private List<Cell> cells;
    private List<RoadSign> roadSigns;
    private Coordinates startCoordinates;
    private Coordinates endCoordinates;
    public Lane(Coordinates startCoordinates, Coordinates endCoordinates) {
        this.cells = new ArrayList<>();
        this.roadSigns = new ArrayList<>();
        this.startCoordinates = startCoordinates;
        this.endCoordinates = endCoordinates;

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
                Math.pow((endCoordinates.x - startCoordinates.x), 2) +
                        Math.pow((endCoordinates.y - startCoordinates.y), 2)
        );
    }

    private Coordinates computeOffsetCoordinates(Coordinates startCoordinates, Coordinates endCoordinates, int cellsAmount, int index) {
        return new Coordinates(
                (int) Math.sqrt(
                        Math.pow((endCoordinates.x - startCoordinates.x), 2)) / cellsAmount * index,
                (int) Math.sqrt(
                        Math.pow((endCoordinates.x - startCoordinates.x), 2)) / cellsAmount * index
        );
    }

    public double computeTrafficWeight() {

    }
}