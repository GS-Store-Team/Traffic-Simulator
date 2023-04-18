package com.traffic_simulator.dto;
import java.util.List;

public record AreaDTO(
        Long id,
        String label,
        List<AreaVersionDTO> versions,
        Boolean canRun
) {}
