package com.traffic_simulator.dto;

import com.traffic_simulator.simulation.models.Coordinate;

public record CarDTO (
        Long id,
        Coordinate coordinates
)
{}
