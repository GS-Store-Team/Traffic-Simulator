package com.traffic_simulator.dto;

import com.traffic_simulator.models.Area;
import com.traffic_simulator.models.AreaVersion;

import java.util.List;
import java.util.stream.Collectors;

public record AreaDTO(
        Long id,
        String label,
        List<AreaVersionDTO> versions,
        Boolean canRun
) {
    public AreaDTO(Area area, List<AreaVersion> versions, Boolean canRun) {
        this(
                area.getId(),
                area.getLabel(),
                versions.stream().map(AreaVersionDTO::new).collect(Collectors.toList()),
                canRun
        );
    }
}
