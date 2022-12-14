package com.traffic_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CarDTO {
    private long id;
    private PointDTO location;

}
