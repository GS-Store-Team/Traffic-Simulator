package com.traffic_simulator.dto;

import com.traffic_simulator.models.Parking;
import com.traffic_simulator.models.Point;

public record ParkingDTO(
        Long id,
        Long capacity,
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

    public ParkingDTO(Parking parking, Boolean valid) {
        this(
                parking.getId(),
                parking.getCapacity(),
                parking.getLocation(),
                valid);
    }
}
