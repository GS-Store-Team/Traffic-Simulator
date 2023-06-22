
package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.dto.SimulationStateDTO;
import com.traffic_simulator.simulation.AreaGraph;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Simulation {
    private List<AreaGraphRunner> runners;
    private PathFindingAlgorithm pathfindingAlgorithm;

    public Simulation(List<AreaGraph> areaGraphs, PathFindingAlgorithm pathfindingAlgorithm) {
        this.runners = areaGraphs.stream().map(AreaGraphRunner::new).toList();
        this.pathfindingAlgorithm = pathfindingAlgorithm;
    }

    public void update(){
        runners.forEach(AreaGraphRunner::update);
    }

    public SimulationStateDTO getState(){
        return new SimulationStateDTO(
                runners.stream().map(r -> r.getState().areaVersionDTO()).flatMap(Collection::stream).collect(Collectors.toList()),
                runners.stream().map(r -> r.getState().cars()).flatMap(Collection::stream).collect(Collectors.toSet())
        );
    }
}
