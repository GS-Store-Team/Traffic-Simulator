package com.traffic_simulator.simulation;

import com.traffic_simulator.models.AreaVersion;
import com.traffic_simulator.simulation.models.SBuilding;
import com.traffic_simulator.simulation.models.SRoad;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AreaGraph {
    private AreaVersion version;
    private List<SRoad> roads = new ArrayList<>();
    private List<SBuilding> buildings = new ArrayList<>();

    public AreaGraph(AreaVersion version) {
        this.version = version;
        this.roads = version.getRoads().stream().map(SRoad::new).toList();
    }
}

