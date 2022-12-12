package com.traffic_simulator.businnes_logic.models.supportive.cell;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class CrossroadCell extends Cell {

    private CrossroadCell upCell;
    private CrossroadCell downCell;
    private CrossroadCell leftCell;
    private CrossroadCell rightCell;

    public CrossroadCell(Coordinates coordinates) {
        super(coordinates);
    }
}
