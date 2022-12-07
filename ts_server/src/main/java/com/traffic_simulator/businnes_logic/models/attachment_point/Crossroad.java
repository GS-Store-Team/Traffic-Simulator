package com.traffic_simulator.businnes_logic.models.attachment_point;

import com.traffic_simulator.businnes_logic.models.road.Lane;
import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.models.signs.crossroad_signs.CrossroadSign;
import com.traffic_simulator.businnes_logic.models.supportive.cell.Cell;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import com.traffic_simulator.businnes_logic.models.supportive.cell.CrossroadCell;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class Crossroad extends AttachmentPoint {
    private List<CrossroadCell> cells;
    private List<Road> roads;
    private Hashtable<Road, List<Lane>> entryLanes;
    private Hashtable<Road, List<Lane>> outputLanes;

    @Setter
    private List<CrossroadSign> roadSigns;
    public Crossroad(Coordinates coordinates) {
        super(coordinates);
        cells = new ArrayList<>();
        roads = new ArrayList<>();
        roadSigns = new ArrayList<>();

        entryLanes = new Hashtable<>();
        outputLanes = new Hashtable<>();

        setEntryLanes();
        setOutputLanes();

    }

    private void setEntryLanes() {
        for (Road road : roads) {
            entryLanes.put(road, road.getRightLanes());
        }
    }

    private void setOutputLanes() {
        for (Road road : roads) {
            entryLanes.put(road, road.getLeftLanes());
        }
    }

    private void generateCrossroadField() {

    }
}
