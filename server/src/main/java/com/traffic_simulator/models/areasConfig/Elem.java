package com.traffic_simulator.models.areasConfig;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Elem{
    private Long areaVersionId;
    private List<Long> cellIds;

    public Elem(Long areaVersionId, List<Long> cellIds) {
        this.areaVersionId = areaVersionId;
        this.cellIds = cellIds;
    }
}