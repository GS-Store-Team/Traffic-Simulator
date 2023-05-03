package com.traffic_simulator.dto;

public record RoadDTO(
        Long id,
        PointDTO start,
        PointDTO end,
        Long forward,
        Integer forwardLanes,
        Long reverse,
        Integer reverseLanes,
        Boolean valid
) {
    public RoadDTO(Long id, RoadDTO roadDTO) {
        this(
                id,
                roadDTO.start,
                roadDTO.end,
                roadDTO.forward,
                roadDTO.forwardLanes,
                roadDTO.reverse,
                roadDTO.reverseLanes,
                roadDTO.valid);
    }
}