package com.traffic_simulator.dto;

import java.util.List;

public record FullMapDTO(
        Long version,
        List<AreaDTO> areas
) {}
