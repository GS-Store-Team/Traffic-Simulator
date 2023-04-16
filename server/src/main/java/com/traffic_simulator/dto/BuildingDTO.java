package com.traffic_simulator.dto;

import com.traffic_simulator.enums.BuildingType;

public record BuildingDTO(
        Long id,
        PointDTO location,
        Double inFlow,
        Double outFlow,
        BuildingType type,
        String label,
        ParkingDTO parking,
        Boolean valid
){}
