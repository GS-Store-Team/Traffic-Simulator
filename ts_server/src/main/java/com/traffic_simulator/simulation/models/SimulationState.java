package com.traffic_simulator.simulation.models;

import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.buildings.*;
import com.traffic_simulator.simulation.models.buildings.types.EntertainmentBuilding;
import com.traffic_simulator.simulation.models.buildings.types.LivingBuilding;
import com.traffic_simulator.simulation.models.buildings.types.PedestrianArea;
import com.traffic_simulator.simulation.models.buildings.types.WorkplaceBuilding;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
//import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.GraphMap;
import com.traffic_simulator.simulation.simulation_runner.algorithms.PathFindingAlgorithm;
import com.traffic_simulator.simulation.graph.GraphMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
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
        for (Building building : graphMap.getBuildings()) {
            if (building.getType() == BuildingType.LIVING) {
                cars.addAll(building.getParkingZone().getCars());
            }
        }
    }


}
