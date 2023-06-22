package com.traffic_simulator.services;

import com.traffic_simulator.dto.SimulationStateDTO;
import com.traffic_simulator.interfaces.SimulationProvider;
import com.traffic_simulator.repository.AreaVersionRepository;
import com.traffic_simulator.simulation.AreaGraph;
import com.traffic_simulator.simulation.simulation_runner.PathFindingAlgorithm;
import com.traffic_simulator.simulation.simulation_runner.Simulation;
import com.traffic_simulator.simulation.simulation_runner.TickGenerator;
import com.traffic_simulator.utils.Converters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SimulationProviderImpl implements SimulationProvider {
    private TickGenerator tickGenerator;
    private Thread currentThread;
    private Simulation simulation;
    private final AreaVersionRepository areaVersionRepository;

    public void build(Map<Long, Long> map) {
        var areaGraphs = map.values()
                 .stream()
                 .map(id -> areaVersionRepository.findById(id).orElseThrow())
                 .map(AreaGraph::new)
                 .collect(Collectors.toList());
        simulation = new Simulation(areaGraphs, new PathFindingAlgorithm());
        tickGenerator = new TickGenerator(simulation);
    }

    public void run(Map<Long, Long> areaIdVersionId){
        destroy();
        build(areaIdVersionId);
        currentThread = new Thread(tickGenerator);
        currentThread.start();
        play();
    }

    public void stop(){
        tickGenerator.stop();
    }

    public void play(){
        tickGenerator.play();
    }
    public void destroy(){
        if(currentThread != null && currentThread.isAlive()){
            currentThread.interrupt();
        }
    }
    public SimulationStateDTO state(){
        return Optional.ofNullable(simulation).orElseThrow().getState();
    }
}
