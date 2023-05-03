package com.traffic_simulator.dto;

import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record CarDTO (
        Long id,
        PointDTO point
)
{}
