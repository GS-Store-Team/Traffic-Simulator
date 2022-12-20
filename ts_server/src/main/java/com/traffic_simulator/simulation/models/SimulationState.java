package com.traffic_simulator.simulation.models;

import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.buildings.*;
import com.traffic_simulator.simulation.models.buildings.types.EntertainmentBuilding;
import com.traffic_simulator.simulation.models.buildings.types.LivingBuilding;
import com.traffic_simulator.simulation.models.buildings.types.PedestrianArea;
import com.traffic_simulator.simulation.models.buildings.types.WorkplaceBuilding;
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
import java.util.List;

@Getter
@Setter
@ToString
public class SimulationState {
    private GraphMap graphMap;
    private PathFindingAlgorithm pathfindingAlgorithm;

    public SimulationState(GraphMap graphMap, PathFindingAlgorithm pathfindingAlgorithm)  {
        this.graphMap = graphMap;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
        init();
    }


    private void init(){
        graphMap.getBuildings().forEach(System.out::println);
    }


}
