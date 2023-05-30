package com.traffic_simulator.dto;

import java.util.List;
import java.util.Set;

public record SimulationStateDTO(
        List<AreaVersionDTO> areaVersionDTO,
        Set<CarDTO> cars
){}
