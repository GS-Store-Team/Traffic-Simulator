package com.traffic_simulator.dto;

import com.traffic_simulator.models.Point;

public record ParkingDTO(
        Long id,
        Integer capacity,
        Point location,
        Boolean valid
) {
    public ParkingDTO(Long id, ParkingDTO parkingDTO) {
        this(
                id,
                parkingDTO.capacity,
                parkingDTO.location,
                parkingDTO.valid);
    }
}
