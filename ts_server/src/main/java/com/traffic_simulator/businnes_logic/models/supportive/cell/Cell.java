package com.traffic_simulator.businnes_logic.models.supportive.cell;

import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString
public class Cell {
    private Coordinates coordinates;

    private double trafficWeight;
    private CellState occupation;
    @Setter
    private Cell nextCell;
    @Setter
    private Cell prevCell;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
        setCellState(CellState.FREE);
    }

    public void setCellState(CellState state) {
        occupation = state;
        setTrafficWeight();
    }

    protected void setTrafficWeight() {
        switch (occupation) {
            case FREE -> trafficWeight = 0;
            case MARKED -> trafficWeight = 0.1;
            case OCCUPIED -> trafficWeight = 1;
        }
    }
}
