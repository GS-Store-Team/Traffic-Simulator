package com.traffic_simulator.businnes_logic.models.supportive.cell;

import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString
public class Cell {
    private Coordinates coordinates;
    @Setter
    private boolean occupied;
    @Setter
    private Cell nextCell;
    @Setter
    private Cell prevCell;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.occupied = false;
    }
}
