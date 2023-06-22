package com.traffic_simulator.simulation.models;

import lombok.Data;
import java.util.List;

@Data
public class AttachmentPoint {
    private List<SBuilding> buildings;
    private List<SRoad> roads;
    public AttachmentPoint(List<SBuilding> buildings, List<SRoad> roads) {
        this.buildings = buildings;
        this.roads = roads;
    }
}
