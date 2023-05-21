
package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.AreaGraphSimulationStateDTO;
import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.repository.AreaRepository;
import com.traffic_simulator.repository.AreaVersionRepository;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.simulation_runner.SimulationConfig;
import com.traffic_simulator.simulation.simulation_runner.SimulationRunner;
import com.traffic_simulator.simulation.simulation_runner.TickGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("state")
@RequiredArgsConstructor
public class SimulationController {

    @Autowired
    private SimulationRunner simulationRunner;

    @Autowired
    private TickGenerator tickGenerator;
    private List<AreaGraph> areaGraphs;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private SimulationConfig simulationConfig;
    private AreaVersionRepository areaVersionRepository;
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
        for (Long areaId : areasToAreaVersionsMap.keySet()) {
            if (areasToAreaVersionsMap.get(areaId) != null) {
                areaGraphs.add(new AreaGraph());
            }
        }
        areaGraphs.forEach(AreaGraph::constructGraphMap);

        simulationConfig.tickGenerator();

        return ResponseEntity.ok(simulationConfig.simulationState());
    }

    @GetMapping("/run")
    public ResponseEntity<?> startSimulation() {
        if (simulationRunner == null) {
            build();
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

    @GetMapping("/area_state")
    public ResponseEntity<AreaGraphSimulationStateDTO> getState() {
        return ResponseEntity.noContent().build();
//        SimulationState simulationState = null;
//        return simulationState != null ?
//                ResponseEntity.ok(simulationState) :
//                ResponseEntity.noContent().build();
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

