package com.traffic_simulator.dto;

import java.util.Set;

public record AreaGraphSimulationStateDTO(
        AreaVersionDTO areaVersionDTO,
        Set<CarDTO> cars
){}
