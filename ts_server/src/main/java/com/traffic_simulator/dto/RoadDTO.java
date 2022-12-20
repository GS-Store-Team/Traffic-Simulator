package com.traffic_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class RoadDTO {
    private long id;
    private PointDTO start;
    private PointDTO end;
    private int forwardLanesCnt = 1;
    private int reverseLanesCnt = 1;

    private List<RoadSignDTO> forwardRoadSigns;

    private List<RoadSignDTO> reverseRoadSigns;
}