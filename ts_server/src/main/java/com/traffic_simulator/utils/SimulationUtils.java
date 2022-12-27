package com.traffic_simulator.utils;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.CarDTO;
import com.traffic_simulator.dto.PointDTO;
import com.traffic_simulator.simulation.graph.GraphMap;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.supportive.Coordinates;

public class SimulationUtils {
    public static CarDTO carToDTO(Car car){
        return new CarDTO(car.getId(), coordinateToPoint(car.getCurrentPosition()), car.getCurrentVelocity(), car.getCurrentAcceleration(), null);
    }

    public static BuildingDTO buildingToDTO(Building building){
        return new BuildingDTO(building.getId(), coordinateToPoint(building.getUpLeftCorner()), building.getType(), 0, 0);
    }

    public static PointDTO coordinateToPoint(Coordinates coordinates){
        return new PointDTO(coordinates.getX(), coordinates.getY());
    }

    public static Coordinates pointToCoordinates(PointDTO pointDTO){
        return new Coordinates(pointDTO.getX(), pointDTO.getY());
    }

    public static NodeNe getNodeFromBuilding(Building building, GraphMap graphMap){
        return null;
    }
}
