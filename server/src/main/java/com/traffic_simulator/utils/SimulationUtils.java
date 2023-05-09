
package com.traffic_simulator.utils;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.CarDTO;
import com.traffic_simulator.dto.PointDTO;
import com.traffic_simulator.simulation.graph.AreaGraph;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.car.Car;
import com.traffic_simulator.simulation.models.supportive.Coordinates;

public class SimulationUtils {
    public static CarDTO carToDTO(Car car) {
        //return new CarDTO(car.getId(), coordinateToPoint(car.getCurrentPosition()));
        return null;
    }

    public static BuildingDTO buildingToDTO(Building building) {
        return null;
        //return new BuildingDTO(building.getId(), coordinateToPoint(building.getUpLeftCorner()), 0D, 0D, building.getType(), building.getName(), building.getParkingZone(), true);
    }

    public static PointDTO coordinateToPoint(long id, Coordinates coordinates) {
        return new PointDTO(id, coordinates.getX(), coordinates.getY());
    }

    public static Coordinates pointToCoordinates(PointDTO pointDTO) {
        return new Coordinates(pointDTO.x(), pointDTO.y());
    }

    public static Node getNodeFromBuilding(Building building, AreaGraph areaGraph) {
        return null;
    }
}

