package com.traffic_simulator.dto;

import com.traffic_simulator.enums.BuildingType;

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
}
