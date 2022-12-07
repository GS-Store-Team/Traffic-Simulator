package com.traffic_simulator.businnes_logic.models.road;

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
    private int trafficWeight;

    public Lane(Coordinates startCoordinates, Coordinates endCoordinates) {
        this.cells = new ArrayList<>();
        this.roadSigns = new ArrayList<>();
        this.startCoordinates = startCoordinates;
        this.endCoordinates = endCoordinates;
    }
}