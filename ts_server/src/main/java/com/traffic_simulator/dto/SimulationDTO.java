package com.traffic_simulator.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
public class SimulationDTO {
    private List<CarDTO> cars;
    private List<TrafficLightDTO> trafficLights;
    private List<BuildingDTO> buildings;
    private List<HeatMapRoadDTO> heatMapRoads;
}