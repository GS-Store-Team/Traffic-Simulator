package com.traffic_simulator.businnes_logic.models.attachment_point;

import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.models.signs.crossroad_signs.CrossroadSign;
import com.traffic_simulator.businnes_logic.models.supportive.cell.Cell;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Crossroad extends AttachmentPoint {
    private List<Cell> cells;
    private List<Road> roads;
    @Setter
    private List<CrossroadSign> roadSigns;
    public Crossroad(Coordinates coordinates) {
        super(coordinates);
        cells = new ArrayList<>();
        roads = new ArrayList<>();
        roadSigns = new ArrayList<>();
    }
}
