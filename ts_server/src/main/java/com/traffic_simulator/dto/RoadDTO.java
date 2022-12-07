package com.traffic_simulator.dto;

import lombok.Data;

@Data
public class RoadDTO {
    private long id;
    private PointDTO start;
    private PointDTO end;
    private int forwardLanesCnt = 1;
    private int reverseLanesCnt = 1;
}
