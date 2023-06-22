package com.traffic_simulator.simulation.models;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SRoad {
    private final long id = 0;
    private Coordinate start;
    private Coordinate end;
    private List<Lane> forward;
    private List<Lane> reverse;
    public SRoad() {
    }
}
