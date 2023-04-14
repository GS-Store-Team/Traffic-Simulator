package com.traffic_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CarDTO {
    private long id;
    private PointDTO location;
    private double velocity;
    private double currentAcceleration;
    private List<RoadDTO> path;
}
