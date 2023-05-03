package com.traffic_simulator.dto;

public record PointDTO(
        Long id,
        Double x,
        Double y
)
{
    public PointDTO (Long id, PointDTO pointDTO) {
        this(id, pointDTO.x, pointDTO.y);
    }
}
