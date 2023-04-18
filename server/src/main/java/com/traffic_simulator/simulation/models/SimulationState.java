package com.traffic_simulator.simulation.models;

import com.traffic_simulator.simulation.graph.GraphMap;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.buildings.types.LivingBuilding;
import com.traffic_simulator.simulation.models.buildings.types.WorkplaceBuilding;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
import com.traffic_simulator.simulation.simulation_runner.algorithms.PathFindingAlgorithm;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class SimulationState {
    private GraphMap graphMap;
    private PathFindingAlgorithm pathfindingAlgorithm;
    private Set<Car> cars;

    public SimulationState(GraphMap graphMap, PathFindingAlgorithm pathfindingAlgorithm) {
        this.graphMap = graphMap;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
        this.cars = new HashSet<>();
        init();
    }

    public List<Road> getAllRoads() {
        return graphMap.getRoads();
    }

    public List<Building> getAllBuildings() {
        return graphMap.getBuildings();
    }

    public List<LivingBuilding> getAllLivingBuildings() {
        return graphMap.getLivingBuildings();
    }

    public List<WorkplaceBuilding> getAllWorkplaceBuildings() {
        return graphMap.getWorkplaceBuildings();
    }

    private void init() {
        /*defaultInit(
                getAllBuildings().stream()
                        .filter(b -> b.getType().equals(BuildingType.LIVING))
                        .toList(),
                getAllBuildings().stream()
                        .filter(b -> b.getType().equals(BuildingType.WORK))
                        .toList());
        */
        defaultInit(getAllLivingBuildings(), getAllWorkplaceBuildings());
        for (Building building : graphMap.getBuildings()) {
            if (building.getType() == BuildingType.LIVING) {
                cars.addAll(building.getParkingZone().getCars());
            }
        }
        System.out.println("Simulation state initialization completed!");
    }

    private void defaultInit(List<LivingBuilding> livingBuildings, List<WorkplaceBuilding> destinationBuildings) {
        AtomicLong id = new AtomicLong();
        List<LivingBuilding> lb = new ArrayList<>(livingBuildings);
        lb.forEach(b -> {
            //ParkingZone parkingZone = b.getParkingZone();
            for (int i = 0; i < b.getResidingCars(); i++)
                b.getParkingZone().addCar(
                        new Car(
                                id.getAndIncrement(),
                                b,
                                destinationBuildings.get(
                                        Math.abs(ThreadLocalRandom.current().nextInt()) % destinationBuildings.size())));
        });
    }
}