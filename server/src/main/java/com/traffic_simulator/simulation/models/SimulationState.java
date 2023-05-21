
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

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class SimulationState {
    private List<AreaGraph> areaGraphs;
    private PathFindingAlgorithm pathfindingAlgorithm;
    private Set<Car> cars;

    private Map<Long, Long> areasToVersionsMap;

    public SimulationState(List<AreaGraph> areaGraphs, PathFindingAlgorithm pathfindingAlgorithm, Map<Long, Long> areasToVersionsMap) {
        this.areaGraphs = areaGraphs;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
        this.cars = new HashSet<>();
        this.areasToVersionsMap = areasToVersionsMap;
        init();
    }

    public List<Road> getAllRoads() {
        return areaGraphs.getRoads();
    }

    public List<Building> getAllBuildings() {
        return areaGraphs.getBuildings();
    }

    public List<LivingBuilding> getAllLivingBuildings() {
        return areaGraphs.getLivingBuildings();
    }

    public List<WorkplaceBuilding> getAllWorkplaceBuildings() {
        return areaGraphs.getWorkplaceBuildings();
    }

    private void init() {
        defaultInit(getAllLivingBuildings(), getAllWorkplaceBuildings());
        for (Building building : areaGraphs.getBuildings()) {
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

    public void update() {

        areaGraphs.update();
    }
}
