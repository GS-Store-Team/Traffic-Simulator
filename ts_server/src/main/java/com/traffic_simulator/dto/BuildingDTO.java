package com.traffic_simulator.dto;

import com.traffic_simulator.simulation.models.supportive.BuildingType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuildingDTO {
    private long id;
    private PointDTO location;
    private BuildingType buildingType;
    private int carsAmount;
    private int carsCap;
}
