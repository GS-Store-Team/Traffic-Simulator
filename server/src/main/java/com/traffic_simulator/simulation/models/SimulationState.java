
package com.traffic_simulator.simulation.models;

import com.traffic_simulator.enums.BuildingType;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.buildings.types.LivingBuilding;
import com.traffic_simulator.simulation.models.buildings.types.WorkplaceBuilding;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.PathFindingAlgorithm;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class SimulationState {
    private AreaGraph areaGraph;
    private PathFindingAlgorithm pathfindingAlgorithm;
    private Set<Car> cars;

    public SimulationState(AreaGraph areaGraph, PathFindingAlgorithm pathfindingAlgorithm) {
        this.areaGraph = areaGraph;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
        this.cars = new HashSet<>();
        init();
    }

    public List<Road> getAllRoads() {
        return areaGraph.getRoads();
    }

    public List<Building> getAllBuildings() {
        return areaGraph.getBuildings();
    }

    public List<LivingBuilding> getAllLivingBuildings() {
        return areaGraph.getLivingBuildings();
    }

    public List<WorkplaceBuilding> getAllWorkplaceBuildings() {
        return areaGraph.getWorkplaceBuildings();
    }

    private void init() {
        defaultInit(getAllLivingBuildings(), getAllWorkplaceBuildings());
        for (Building building : areaGraph.getBuildings()) {
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
