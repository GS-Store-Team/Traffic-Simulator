package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.models.SimulationState;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;
import com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.StraightDijkstraAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class SimulationConfig {

    /*@Autowired
    private StraightDijkstraAlgorithm straightDijkstraAlgorithm;

    private List<AreaGraph> areaGraphs;

    @Bean
    @Scope("prototype")
    public TickGenerator tickGenerator() {
        return new TickGenerator(simulationRunner());
    }
    @Bean
    @Scope("prototype")
    public SimulationRunner simulationRunner() {
        return new SimulationRunner(simulationState(), simulationSettings());
    }

    @Bean
    @Scope("singleton")
    public SimulationState simulationState() {
        return new SimulationState(areaGraphs, straightDijkstraAlgorithm);
    }

    @Bean
    @Scope("prototype")
    public SimulationSettings simulationSettings() {
        return new SimulationSettings();
    }*/
}
