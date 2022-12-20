package com.traffic_simulator.dto;

import com.traffic_simulator.enums.TrafficLight;
import lombok.Data;

@Data
public class TrafficLightDTO {
    private long id;
    private TrafficLight trafficLight;
}
