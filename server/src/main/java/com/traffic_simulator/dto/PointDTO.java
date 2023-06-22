package com.traffic_simulator.dto;

import com.traffic_simulator.models.Point;
import com.traffic_simulator.simulation.models.Coordinate;

public record PointDTO(
        Long id,
        Double x,
        Double y
)
{

    public PointDTO (Point point) {
        this(point.getId(), point.getX(), point.getY());
    }

    public PointDTO(Coordinate coordinate) {
        this(0L, coordinate.x(), coordinate.y());
    }
}
