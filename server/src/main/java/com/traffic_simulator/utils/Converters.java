package com.traffic_simulator.utils;

import com.traffic_simulator.dto.*;
import com.traffic_simulator.models.AreaVersion;
import com.traffic_simulator.simulation.models.Car;
import com.traffic_simulator.simulation.models.Coordinate;
import com.traffic_simulator.simulation.models.SBuilding;

public class Converters {
    public static AreaVersionDTO toAreaVersionDTO(AreaVersion areaVersion) {
        return new AreaVersionDTO(
                areaVersion.getArea().getId(),
                areaVersion.getId(),
                new UserDTO(
                        areaVersion.getUsr().getId(),
                        areaVersion.getUsr().getName()),
                areaVersion.getLocked(),
                areaVersion.getCreated(),
                areaVersion.getEdited(),
                areaVersion.getLabel(),
                areaVersion.getBuildings().stream().map(b -> new BuildingDTO(b, true)).toList(),
                areaVersion.getRoads().stream().map(r -> new RoadDTO(r, true)).toList(),
                true);
    }

    public static CarDTO carToDTO(Car car) {
        //return new CarDTO(car.getId(), coordinateToPoint(car.getCurrentPosition()));
        return null;
    }

    public static BuildingDTO buildingToDTO(SBuilding building) {
        return null;
        //return new BuildingDTO(building.getId(), coordinateToPoint(building.getUpLeftCorner()), 0D, 0D, building.getType(), building.getName(), building.getParkingZone(), true);
    }

    public static PointDTO coordinateToPoint(Coordinate coordinate) {
        return new PointDTO(coordinate);
    }

    public static Coordinate pointToCoordinates(PointDTO pointDTO) {
        return new Coordinate(pointDTO.x(), pointDTO.y());
    }
}
