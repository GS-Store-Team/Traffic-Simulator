package com.traffic_simulator.dto;

import com.traffic_simulator.simulation.models.supportive.Coordinates;

public record CarDTO (
        Long id,
        Coordinates coordinates
)
{}
