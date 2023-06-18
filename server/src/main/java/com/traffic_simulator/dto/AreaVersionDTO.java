package com.traffic_simulator.dto;

import com.traffic_simulator.models.AreaVersion;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public record AreaVersionDTO(
        Long areaId,
        Long id,
        UserDTO usr,
        Boolean locked,
        Timestamp created,
        Timestamp edited,
        String label,
        List<BuildingDTO> buildings,
        List<RoadDTO> roads,
        Boolean valid
){
    public AreaVersionDTO(AreaVersion areaVersion) {
        this(
                areaVersion.getArea().getId(),
                areaVersion.getId(),
                new UserDTO(areaVersion.getUsr().getId(), areaVersion.getUsr().getName()),
                areaVersion.getLocked(),
                areaVersion.getCreated(),
                areaVersion.getEdited(),
                areaVersion.getLabel(),
                areaVersion.getBuildings().stream().map(b -> new BuildingDTO(b, true)).collect(Collectors.toList()),
                areaVersion.getRoads().stream().map(r -> new RoadDTO(r, true)).collect(Collectors.toList()),
                true
        );
    }
}
