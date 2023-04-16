package com.traffic_simulator.dto;
public record RoadDTO(
        Long id,
        PointDTO start,
        PointDTO end,
        Long forward,
        Long reverse,
        Boolean valid
){}