package com.traffic_simulator.simulation.models;

import com.traffic_simulator.simulation.models.buildings.*;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
//import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.simulation.simulation_runner.algorithms.PathFindingAlgorithm;
import com.traffic_simulator.simulation.graph.GraphMap;
import com.traffic_simulator.utils.SimulationUtils;
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

    public SimulationState(GraphMap graphMap, PathFindingAlgorithm pathfindingAlgorithm)  {
        this.graphMap = graphMap;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
        this.cars = new HashSet<>();
        init();
    }

    public List<Road> getAllRoads(){
        return graphMap.getRoads();
    }

    public List<Building> getAllBuildings(){
        return graphMap.getBuildings();
    }

    private void init(){
        defaultInit(getAllBuildings().stream().filter(b -> b.getType().equals(BuildingType.LIVING)).toList(),
                getAllBuildings().stream().filter(b -> b.getType().equals(BuildingType.WORK)).toList());

        for (Building building : graphMap.getBuildings()) {
            if (building.getType() == BuildingType.LIVING) {
                cars.addAll(building.getParkingZone().getCars());
            }
        }
    }

    private void defaultInit(List<Building> livingBuildings, List<Building> destinationBuildings){
        AtomicLong id = new AtomicLong();
        List<Building> lb = new ArrayList<>(livingBuildings);
        lb.forEach(b -> {
            ParkingZone parkingZone = b.getParkingZone();
            for (int i = 0; i<10; i++ )
                parkingZone.addCar(new Car(id.getAndIncrement(), b, destinationBuildings.get(Math.abs(ThreadLocalRandom.current().nextInt()) % destinationBuildings.size())));
        });
    }
}