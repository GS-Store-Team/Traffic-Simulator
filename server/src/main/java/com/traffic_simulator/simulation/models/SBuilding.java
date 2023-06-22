package com.traffic_simulator.simulation.models;

import com.traffic_simulator.enums.BuildingType;
import lombok.Data;

import java.util.List;

@Data
public class SBuilding {
    private final long id = 0;
    private final BuildingType type = BuildingType.LIVING;
    private List<Car> cars;
    private Coordinate coordinate;
    private AttachmentPoint connectedPoint = null;

    public SBuilding() {
    }
}
