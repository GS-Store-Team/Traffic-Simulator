package com.traffic_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BuildingDTO {
    private long id;

    private PointDTO location;
}
