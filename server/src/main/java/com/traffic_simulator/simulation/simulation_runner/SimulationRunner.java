
package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.enums.BuildingType;
import com.traffic_simulator.exceptions.GraphConstructionException;
import com.traffic_simulator.exceptions.SimulationException;
import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.models.SimulationState;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.car.Navigator;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimulationRunner {
    private final SimulationState simulationState;
    private SimulationSettings simulationSettings;
    private List<Car> cars;
    private List<Navigator> navigators;
    private long currentTick;

    public SimulationRunner(SimulationState simulationState, SimulationSettings simulationSettings) {
        this.simulationState = simulationState;
        this.simulationSettings = simulationSettings;
        this.cars = new ArrayList<>();
        this.cars.addAll(this.simulationState.getCars());
        this.navigators = new ArrayList<>();
        this.currentTick = 0;
        init();
    }

    private void init() {
        try {
            initCars();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //"initialization failure"
        }
    }

    public void reset() {

    }

    public void update() throws SimulationException {
        //System.out.println("UPDATE TICK# " + currentTick);
        updateNavigators(currentTick);

        if (currentTick >= GlobalSettings.dayLengthInSeconds) {
            currentTick %= GlobalSettings.dayLengthInSeconds;
        }
        currentTick++;
    }

//    public SimulationDTO getCurrentSimulationState() {
//        SimulationDTO simulationDTO = new SimulationDTO();
//        simulationDTO.setCars(cars.stream().map(SimulationUtils::carToDTO).toList());
//        simulationDTO.setBuildings(simulationState.getAllBuildings().stream().map(SimulationUtils::buildingToDTO).toList());
//        return simulationDTO;
//    }

    private void updateNavigators(long secondsPassed) {
        for (Navigator navigator : navigators) {
            navigator.update(secondsPassed);
        }
    }

//    private void setCarsNavigators(){
//        navigators = cars.stream().map(car -> {
//            NodeNe start = SimulationUtils.getNodeFromBuilding(car.getBuildingStart(), simulationState.getGraphMap());
//            NodeNe end = SimulationUtils.getNodeFromBuilding(car.getBuildingEnd(), simulationState.getGraphMap());
//
//            CarPath carPath = PathRetriever.retrievePath(start, end, null); // 3rd param shouldn't be null
//            if(carPath == null) throw new RuntimeException("invalid car path");
//
//            return new Navigator(
//                    car,
//                    simulationSettings.getAutomobileMinAcceleration(),
//                    simulationSettings.getAutomobileMaxAcceleration(),
//                    carPath);
//        }).toList();
//    }

    private void initCars() throws GraphConstructionException {

        HashMap<Node, CarPathsBunch> allPaths = simulationState.getPathfindingAlgorithm().compute();
        List<Car> unmarkedCars = new ArrayList<>(cars);
        int tempId = 1;
        int cycle = 1;
        long departureTime = 0;
        int carPackSize = simulationSettings.getSeedData().depCarPackSize();
        List<Node> ends = simulationState.getAreaGraph().getNodes()
                .stream()
                .filter((Node n) -> !n.getAttachmentPoint().getConnectedBuildings()
                        .stream()
                        .filter((Building b) -> !b.getType().equals(BuildingType.LIVING))
                        .toList()
                        .isEmpty())
                .toList();

        while (!unmarkedCars.isEmpty()) {
            for (int i = 0; i < unmarkedCars.size(); i = (i + carPackSize) % unmarkedCars.size()) {
                Car currentCar = unmarkedCars.get(i);
                Node startPoint = simulationState.getAreaGraph().getNodes().stream()
                        .filter((Node n) -> n.getAttachmentPoint()
                                .getConnectedBuildings()
                                .contains(currentCar.getBuildingStart()))
                        .toList()
                        .get(0);
                Node endPoint = ends.get((simulationSettings.getSeedData().coeff() * 10 / cycle + i) % ends.size());
                int temp = 1;
                while (endPoint == startPoint) {
                    endPoint = ends.get((simulationSettings.getSeedData().coeff() * 10 / cycle + i + temp) % ends.size());
                    temp++;
                }

                Navigator navigator = new Navigator(unmarkedCars.get(i),
                        20,
                        -20,
                        allPaths.get(startPoint).getCarPathsByEnds().get(endPoint));

                navigator.setDepartureTime((departureTime + simulationSettings.getSeedData().depTimeShift()) % GlobalSettings.dayLengthInSeconds);
                navigator.setWorkTime((simulationSettings.getSeedData().coeff() * simulationSettings.getSeedData().destTimeSpend()) % GlobalSettings.dayLengthInSeconds);

                navigators.add(navigator);
                unmarkedCars.remove(i);
                if (unmarkedCars.isEmpty()) {
                    break;
                }
            }
            departureTime = (departureTime + simulationSettings.getSeedData().depTimeShift()) % GlobalSettings.dayLengthInSeconds;
            cycle++;
        }

        System.out.println("Cars initialization completed!");
    }
}

