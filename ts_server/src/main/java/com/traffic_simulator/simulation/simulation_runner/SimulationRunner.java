package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.dto.SimulationDTO;
import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.exceptions.SimulationException;
import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.SimulationState;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.car.Navigator;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimulationRunner {
    private final SimulationState roadMap;
    private SimulationSettings simulationSettings;
    private List<Car> cars;
    private List<Navigator> navigators;

    public SimulationRunner(SimulationState roadMap, SimulationSettings simulationSettings) {
        this.roadMap = roadMap;
        this.simulationSettings = simulationSettings;
        this.cars = new ArrayList<>();
        this.cars.addAll(this.roadMap.getCars());
        this.navigators = new ArrayList<>();
    }

    public void reset() {

    }

    public void update() throws SimulationException {
        updateNavigators();
    }

    public SimulationDTO getCurrentSimulationState() {
        //return new SimulationDTO();
        return null;
    }

    private void updateNavigators() {
        for (Navigator navigator : navigators) {
            navigator.update();
        }
    }

    private void initCars() throws GraphConstructionException {

        HashMap<NodeNe, CarPathsBunch> allPaths = roadMap.getPathfindingAlgorithm().compute();
        List<Car> unmarkedCars = new ArrayList<>(cars);
        int tempId = 1;
        int cycle = 1;
        long departureTime = 0;
        int carPackSize = simulationSettings.seedData.depCarPackSize();
        List<NodeNe> ends = roadMap.getGraphMap().getBuildingNodes().stream().filter((NodeNe n) -> n.getAttachmentPoint().getConnectedBuildings().get(0).getType() != BuildingType.LIVING).toList();

        while (!unmarkedCars.isEmpty()) {
            for (int i = 0; i < unmarkedCars.size(); i = (i + carPackSize) % unmarkedCars.size()) {
                Car currentCar = unmarkedCars.get(i);
                NodeNe startPoint = roadMap.getGraphMap().getBuildingNodes().stream()
                        .filter((NodeNe n) -> n.getAttachmentPoint()
                                .getConnectedBuildings()
                                .contains(currentCar.getBuildingStart()))
                        .toList()
                        .get(0);
                NodeNe endPoint = ends.get((simulationSettings.seedData.coeff() * 10 / cycle + i) % ends.size());
                Navigator navigator = new Navigator(unmarkedCars.get(i), 20, -20, allPaths.get(startPoint).getCarPathsEndsMap().get(endPoint));

                navigator.setDepartureTime((departureTime + simulationSettings.seedData.depTimeShift()) % GlobalSettings.dayLengthInSeconds);
                navigator.setWorkTime((simulationSettings.seedData.coeff() * simulationSettings.seedData.destTimeSpend()) % GlobalSettings.dayLengthInSeconds);

                navigators.add(navigator);
                unmarkedCars.remove(i);
            }
            departureTime = (departureTime + simulationSettings.seedData.depTimeShift()) % GlobalSettings.dayLengthInSeconds;
            cycle++;
        }
    }

    private void initGraphMap() throws InvalidMapException {
        //PathfindingAlgorithm pathfindingAlgorithm = new StraightDijkstraAlgorithm(graphMap);
    }
}