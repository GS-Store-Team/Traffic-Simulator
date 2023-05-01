package com.traffic_simulator.dto;

import java.sql.Timestamp;
import java.util.List;

public record AreaVersionDTO(
        Long id,
        UserDTO usr,
        Boolean locked,
        Timestamp created,
        Timestamp edited,
        String label,
        List<BuildingDTO> buildings,
        List<RoadDTO> roads
){}
