package com.traffic_simulator.simulation.models;

import com.traffic_simulator.enums.CellState;
import lombok.Data;
@Data
public class Cell {
    private final Coordinate coordinates;
    private Car car = null;

    public Cell(Coordinate coordinates) {
        this.coordinates = coordinates;
    }
}
