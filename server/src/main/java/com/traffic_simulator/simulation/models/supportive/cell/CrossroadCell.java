package com.traffic_simulator.simulation.models.supportive.cell;

import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class CrossroadCell extends Cell {

    private CrossroadCell upCell;
    private CrossroadCell downCell;
    private CrossroadCell leftCell;
    private CrossroadCell rightCell;

    private int x;
    private int y;

    private boolean occupied = false;

    public CrossroadCell(Coordinates coordinates, int x, int y) {
        super(coordinates);
        this.x = x;
        this.y = y;
    }
}
