package com.traffic_simulator.dto;

import com.traffic_simulator.models.Point;

public record PointDTO(
        Long id,
        Double x,
        Double y
)
{
    public PointDTO (Long id, PointDTO pointDTO) {
        this(id, pointDTO.x, pointDTO.y);
    }

    public PointDTO (Point point) {
        this(point.getId(), point.getX(), point.getY());
    }

    public PointDTO(Long id, int x, int y) {
        this(id, (double)x, (double)y);
    }
}
