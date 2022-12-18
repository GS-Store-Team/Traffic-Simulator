package com.traffic_simulator.businnes_logic.simulation_runner;

import com.traffic_simulator.businnes_logic.models.RoadMap;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.buildings.ParkingZone;
import com.traffic_simulator.businnes_logic.models.car.Car;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.PathfindingAlgorithm;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.StraightDijkstraAlgorithm;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.Node;
import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.exceptions.SimulationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimulationRunner {
    private final RoadMap roadMap;
    private HashMap<Node, CarPathsBunch> allPaths;
    private GraphMap graphMap;
    private List<Car> cars;
    public SimulationRunner(RoadMap roadMap) {
        this.roadMap = roadMap;
        this.allPaths = new HashMap<>();
        this.graphMap = null;
        this.cars = new ArrayList<>();
    }

    public void reset() {
        allPaths = new HashMap<>();
        graphMap = null;
        cars = new ArrayList<>();
    }

    public void update() {
        for (Car car : cars) {
            car.update();
        }
    }
    private void heavyInit() throws SimulationException {
        try {
            initGraphMap();
            initCars();
        } catch (Exception exc) {
            throw new SimulationException("Simulation initialization failed!", exc);
        }
    }

    private void initCars() {
        List<ParkingZone> parkingZones = new ArrayList<>();
        for (Building building : roadMap.getBuildings()) {
            parkingZones.add(building.getParkingZone());
        }

        for (ParkingZone parkingZone : parkingZones) {
            cars.addAll(parkingZone.getCars());
        }

        for (Car car : cars) {
            //TODO Раздать машинкам их пути, соответствующие их точке старта и финиша из CarPathBunch
        }
    }

    private void initGraphMap() throws InvalidMapException {
        try {
            graphMap = new GraphMap(roadMap);
            PathfindingAlgorithm pathfindingAlgorithm = new StraightDijkstraAlgorithm(graphMap);
            allPaths = pathfindingAlgorithm.compute();
        } catch (GraphConstructionException exc) {
            throw new InvalidMapException("Cannot build graph from roadmap!", exc.getUnreachableNodes());
        }
    }

    public SimulationDTO getCurrentSimulationState() {
        return new SimulationDTO();
    }

}
