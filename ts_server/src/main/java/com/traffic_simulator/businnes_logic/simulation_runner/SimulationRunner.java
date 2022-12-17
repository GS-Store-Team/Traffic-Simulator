package com.traffic_simulator.businnes_logic.simulation_runner;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.PathfindingAlgorithm;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.StraightDijkstraAlgorithm;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.Node;
import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.exceptions.SimulationException;

import java.util.HashMap;

public class SimulationRunner {
    private final RoadMap roadMap;
    private HashMap<Node, CarPathsBunch> allPaths;
    private GraphMap graphMap = null;
    public SimulationRunner(RoadMap roadMap) {
        this.roadMap = roadMap;
        this.allPaths = new HashMap<>();
    }

    public void start() {
        //heavy initialization
        play();
    }

    public void play() {
    }

    public void stop() {
    }

    private void init() throws SimulationException {
        try {
            initGraphMap();
        } catch (Exception exc) {
            throw new SimulationException("Simulation initialization failed!", exc);
        }
    }
    private GraphMap initGraphMap() throws InvalidMapException {
        try {
            graphMap = new GraphMap(roadMap);
            PathfindingAlgorithm pathfindingAlgorithm = new StraightDijkstraAlgorithm(graphMap);
            allPaths = pathfindingAlgorithm.compute();
        } catch (GraphConstructionException exc) {
            throw new InvalidMapException("Cannot build graph from roadmap!", exc.getUnreachableNodes());
        }

        return graphMap;
    }

    public SimulationDTO getCurrentSimulationState() {
        return new SimulationDTO();
    }

}
