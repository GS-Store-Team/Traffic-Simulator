package com.traffic_simulator.businnes_logic.models.attachment_point;

import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class BuildingConnector extends AttachmentPoint {
    private List<Building> connectedBuildings;
    public BuildingConnector(Coordinates coordinates) {
        super(coordinates);
    }
}
