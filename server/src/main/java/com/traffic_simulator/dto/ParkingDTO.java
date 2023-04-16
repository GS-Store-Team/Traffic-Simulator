package com.traffic_simulator.dto;

import com.traffic_simulator.models.Point;

public record ParkingDTO(
        Long id,
        Long capacity,
        Point location,
        Boolean valid
){}
