package com.traffic_simulator.dto;

import com.traffic_simulator.models.Road;

public record RoadDTO(
        Long id,
        PointDTO start,
        PointDTO end,
        Long forward,
        Long reverse,
        Boolean valid
) {
    public RoadDTO(Long id, RoadDTO roadDTO) {
        this(
                id,
                roadDTO.start,
                roadDTO.end,
                roadDTO.forward,
                roadDTO.reverse,
                roadDTO.valid);
    }

    public RoadDTO(Road road, Boolean valid) {
        this(
                road.getId(),
                new PointDTO(road.getStart()),
                new PointDTO(road.getEnd()),
                road.getForward(),
                road.getReverse(),
                valid
                );
    }
}