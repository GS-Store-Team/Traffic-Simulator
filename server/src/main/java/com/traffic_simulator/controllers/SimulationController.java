
package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.dto.SimulationStateDTO;
import com.traffic_simulator.models.Area;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("state")
@RequiredArgsConstructor
public class SimulationController {

    private SimulationRunner simulationRunner;

    private TickGenerator tickGenerator;
    private List<AreaGraph> areaGraphs = new ArrayList<>();

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaVersionRepository areaVersionRepository;
    private Map<Long, Long> areasToAreaVersionsMap;
    /*@SneakyThrows
    //@PostConstruct
    private void init() {
        this.areaGraph = new AreaGraph(areaSimulationContext);

        areaGraph.constructGraphMap();

        simulationConfig.tickGenerator();
        this.pathFindingAlgorithm = new StraightDijkstraAlgorithm(this.areaGraph);
        this.roadMap = new SimulationState(areaGraph, pathFindingAlgorithm);
        this.simulationRunner = new SimulationRunner(roadMap, new SimulationSettings());
        this.tickGenerator = new TickGenerator(simulationRunner);
    }*/

    @GetMapping("/build")
    public ResponseEntity<?> build(Map<Long, Long> areasToAreaVersionsMap) {
        this.areasToAreaVersionsMap = areasToAreaVersionsMap;
        for (Long areaId : areasToAreaVersionsMap.keySet()) {
            if (areasToAreaVersionsMap.get(areaId) != null) {
                Area area = areaRepository.findById(areaId).orElseThrow();
                areaGraphs.add(new AreaGraph(area, areasToAreaVersionsMap.get(areaId)));
            }
        }
        areaGraphs.forEach(AreaGraph::constructGraphMap);

        simulationRunner = new SimulationRunner(
                new SimulationState(areaGraphs, new StraightDijkstraAlgorithm(), areasToAreaVersionsMap),
                new SimulationSettings());
        tickGenerator = new TickGenerator(simulationRunner);

        return ResponseEntity.ok(Converters.simulationStateDTO(tickGenerator.getSimulationRunner().getSimulationState()));
    }

    @GetMapping("/run")
    public ResponseEntity<?> startSimulation() {
        if (simulationRunner == null) {
            build(areasToAreaVersionsMap);
        } else simulationRunner.reset();

        new Thread(tickGenerator).start();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/stop")
    public ResponseEntity<?> stopSimulation() {
        if (simulationRunner == null) return ResponseEntity.badRequest().build();
        tickGenerator.stop();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/run/play")
    public ResponseEntity<?> playSimulation() {
        if (simulationRunner == null) return ResponseEntity.badRequest().build();
        tickGenerator.play();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/simulation_state")
    public ResponseEntity<SimulationStateDTO> getState() {
        SimulationState simulationState = tickGenerator.getSimulationRunner().getSimulationState();
        return simulationState != null ?
                ResponseEntity.ok(Converters.simulationStateDTO(simulationState)) :
                ResponseEntity.noContent().build();
    }

    @GetMapping("/config")
    public ResponseEntity<FullMapDTO> getMapConfig() {
        return ResponseEntity.noContent().build();
//        var mapState = areaGraph.getCurrentMapConfig();
//        return mapState != null ?
//                ResponseEntity.ok(mapState) :
//                ResponseEntity.noContent().build();
    }
}

