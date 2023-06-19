package com.traffic_simulator.services;

import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.dto.SimulationStateDTO;
import com.traffic_simulator.interfaces.SimulationProvider;
import com.traffic_simulator.models.Area;
import com.traffic_simulator.models.AreaVersion;
import com.traffic_simulator.repository.AreaRepository;
import com.traffic_simulator.repository.AreaVersionRepository;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.models.SimulationState;
import com.traffic_simulator.simulation.simulation_runner.SimulationRunner;
import com.traffic_simulator.simulation.simulation_runner.TickGenerator;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;
import com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.StraightDijkstraAlgorithm;
import com.traffic_simulator.utils.Converters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
@SessionScope
public class SimulationProviderImpl implements SimulationProvider {
    private SimulationRunner simulationRunner;
    private TickGenerator tickGenerator;
    private Thread currentThread;

    private final AreaRepository areaRepository;

    private final AreaVersionRepository areaVersionRepository;


    public void build(Map<Long, Long> areaIdVersionId) {
         var areaGraphs = new ArrayList<AreaGraph>();

        for (Long areaId : areaIdVersionId.keySet()) {
            if (areaIdVersionId.get(areaId) != null) {
                Area area = areaRepository.findById(areaId).orElseThrow();
                areaGraphs.add(new AreaGraph(area, areaIdVersionId.get(area.getId())));
            }
         }
        areaGraphs.forEach(AreaGraph::constructGraphMap);

        simulationRunner = new SimulationRunner(
                new SimulationState(areaGraphs, new StraightDijkstraAlgorithm(), areaIdVersionId),
                new SimulationSettings()
        );
        tickGenerator = new TickGenerator(simulationRunner);
    }

    public void run(Map<Long, Long> areaIdVersionId){
        destroy();
        build(areaIdVersionId);
        currentThread = new Thread(tickGenerator);
        currentThread.start();
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
        return Converters.simulationStateDTO(simulationRunner.getSimulationState());
    }
}
