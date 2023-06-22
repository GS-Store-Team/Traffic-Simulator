
package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.dto.SimulationStateDTO;
import com.traffic_simulator.simulation.AreaGraph;
import com.traffic_simulator.simulation.models.Car;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;

@Getter
public class AreaGraphRunner {
    private final AreaGraph graph;
    private List<Car> cars;

    public AreaGraphRunner(AreaGraph graph) {
        this.graph = graph;
    }

    public void update() {
    }

    public SimulationStateDTO getState(){
        return new SimulationStateDTO(List.of(), new HashSet<>());
    }
}

