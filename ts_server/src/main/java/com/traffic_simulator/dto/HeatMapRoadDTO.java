package com.traffic_simulator.dto;

import com.traffic_simulator.enums.RoadLoad;
import lombok.Data;

@Data
public class HeatMapRoadDTO {
    private long roadId;
    private RoadLoad roadLoad;
}
