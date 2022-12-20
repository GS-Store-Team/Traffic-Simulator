package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.exceptions.SimulationException;
import com.traffic_simulator.simulation.models.SimulationState;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;

public class SimulationRunner {
    private final SimulationState roadMap;
    private SimulationSettings simulationSettings;
    public SimulationRunner(SimulationState roadMap, SimulationSettings simulationSettings) {
        this.roadMap = roadMap;
        this.simulationSettings = simulationSettings;
    }
    public void reset() {

    }

    public void update() {
    }

    public SimulationDTO getCurrentSimulationState(){
        //return new SimulationDTO();
        return null;
    }

    private void heavyInit() throws SimulationException {
//        try {
//            initGraphMap();
//            initCars();
//        } catch (Exception exc) {
//            throw new SimulationException("Simulation initialization failed!", exc);
//        }
    }

    private void initCars() {
//        List<ParkingZone> parkingZones = new ArrayList<>();
//        for (Building building : roadMap.getBuildings()) {
//            parkingZones.add(building.getParkingZone());
//        }
//        for (ParkingZone parkingZone : parkingZones) {
//            cars.addAll(parkingZone.getCars());
//        }
//        for (Car car : cars) {
//        }
    }

    private void initGraphMap() throws InvalidMapException {
        //PathfindingAlgorithm pathfindingAlgorithm = new StraightDijkstraAlgorithm(graphMap);
    }
}