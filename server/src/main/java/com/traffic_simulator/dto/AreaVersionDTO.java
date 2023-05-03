package com.traffic_simulator.dto;

import java.sql.Timestamp;
import java.util.List;

public record AreaVersionDTO(
        Long id,
        UserDTO user,
        Boolean locked,
        Timestamp created,
        Timestamp edited,
        String label,
        List<BuildingDTO> buildings,
        List<RoadDTO> roads,
        Boolean valid
){}
