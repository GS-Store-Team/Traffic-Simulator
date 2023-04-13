package com.traffic_simulator.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Area {
    private Long id;
    private Long MapId;
    private List<AreaVersion> versions;
}
