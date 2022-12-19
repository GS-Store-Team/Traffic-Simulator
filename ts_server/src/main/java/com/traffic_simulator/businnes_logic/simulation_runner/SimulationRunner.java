package com.traffic_simulator.businnes_logic.simulation_runner;

import com.traffic_simulator.businnes_logic.models.RoadMap;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.buildings.ParkingZone;
import com.traffic_simulator.businnes_logic.models.car.Car;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.PathfindingAlgorithm;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.StraightDijkstraAlgorithm;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.exceptions.SimulationException;
import java.util.ArrayList;
import java.util.List;

public class SimulationRunner {
    private final RoadMap roadMap;
    //private GraphMap graphMap;
    private SimulationSettings simulationSettings;
    private List<Car> cars;
    public SimulationRunner(RoadMap roadMap, SimulationSettings simulationSettings) {
        this.roadMap = roadMap;
        //this.graphMap = null;
        this.cars = new ArrayList<>();
        this.simulationSettings = simulationSettings;
    }

    public void reset() {
        //graphMap = null;
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
        }
    }

    private void initGraphMap() throws InvalidMapException {
        //PathfindingAlgorithm pathfindingAlgorithm = new StraightDijkstraAlgorithm(graphMap);
    }

    public SimulationDTO getCurrentSimulationState() {
        return new SimulationDTO();
    }

}
