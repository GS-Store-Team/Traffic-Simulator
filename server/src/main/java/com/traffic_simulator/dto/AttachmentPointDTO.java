package com.traffic_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AttachmentPointDTO {
    private long id;
    private PointDTO location;
    private List<BuildingDTO> connectedBuildings;
    private List<RoadDTO> connectedRoads;
}