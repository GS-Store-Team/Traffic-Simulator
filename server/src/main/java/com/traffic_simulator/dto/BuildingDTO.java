package com.traffic_simulator.dto;

import com.traffic_simulator.enums.BuildingType;
import com.traffic_simulator.models.Building;

public record BuildingDTO(
        Long id,
        PointDTO location,
        Double inFlow,
        Double outFlow,
        Integer residents,
        BuildingType type,
        String label,
        ParkingDTO parking,
        Boolean valid
) {
    public BuildingDTO(Long id, BuildingDTO buildingDTO) {
        this(
                id,
                buildingDTO.location,
                buildingDTO.inFlow,
                buildingDTO.outFlow,
                buildingDTO.residents,
                buildingDTO.type,
                buildingDTO.label,
                buildingDTO.parking,
                buildingDTO.valid);
    }
    public BuildingDTO(Building building, Boolean valid) {
        this(
                building.getId(),
                new PointDTO(building.getLocation()),
                building.getInFlow(),
                building.getOutFlow(),
                0,
                building.getType(),
                building.getLabel(),
                new ParkingDTO(building.getParking(), true),
                valid);
    }
}
