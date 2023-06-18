package com.traffic_simulator.utils;

import com.traffic_simulator.dto.*;
import com.traffic_simulator.models.AreaVersion;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.models.SimulationState;

import java.util.List;
import java.util.stream.Collectors;

public class Converters {
    public static SimulationStateDTO simulationStateDTO(SimulationState simulationState) {
        List<AreaVersionDTO> areaVersionDTOS = simulationState.getAreaGraphs().stream().map(AreaGraph::getAreaVersionDTO).toList();
        return new SimulationStateDTO(areaVersionDTOS, simulationState.getCars().stream()
                .map(c -> new CarDTO(c.getId(), c.getCurrentPosition()))
                .collect(Collectors.toSet())
        );
    }

    public static AreaVersionDTO toAreaVersionDTO(AreaVersion areaVersion) {
        return new AreaVersionDTO(
                areaVersion.getArea().getId(),
                areaVersion.getId(),
                new UserDTO(
                        areaVersion.getUsr().getId(),
                        areaVersion.getUsr().getName()),
                areaVersion.getLocked(),
                areaVersion.getCreated(),
                areaVersion.getEdited(),
                areaVersion.getLabel(),
                areaVersion.getBuildings().stream().map(b -> new BuildingDTO(b, true)).toList(),
                areaVersion.getRoads().stream().map(r -> new RoadDTO(r, true)).toList(),
                true);
    }
}
