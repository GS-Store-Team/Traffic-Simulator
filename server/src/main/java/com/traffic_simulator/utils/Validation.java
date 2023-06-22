package com.traffic_simulator.utils;

import com.traffic_simulator.dto.*;
import com.traffic_simulator.models.areasConfig.AreasPlacement;
import com.traffic_simulator.models.areasConfig.Elem;
import com.traffic_simulator.simulation.GlobalSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validation {
    public static final int BUILDING_WIDTH = 50;
    public static final int CELL_SIZE = 300;
    public static final int CELLS_COUNT = 20;
    private static boolean versionValid = true;

    public static FullMapDTO validate(FullMapDTO fullMapDTO) {
        List<AreaDTO> areas = new ArrayList<>();
        for (AreaDTO areaDTO : fullMapDTO.areas()) {
            AreaDTO newArea = validateArea(areaDTO);
            areas.add(newArea);
        }
        return new FullMapDTO(fullMapDTO.version(), areas);
    }

    private static AreaDTO validateArea(AreaDTO areaDTO) {
        List<AreaVersionDTO> versions = new ArrayList<>();
        boolean canRun = false;
        for (AreaVersionDTO areaVersion : areaDTO.versions()) {
            AreaVersionDTO newAreaVersion = validateAreaVersion(areaVersion, areaDTO.id());
            if (newAreaVersion.valid() && !newAreaVersion.locked())
                canRun = true;
            versions.add(newAreaVersion);
        }
        return new AreaDTO(areaDTO.id(), areaDTO.label(), versions, canRun);
    }

    private static AreaVersionDTO validateAreaVersion(AreaVersionDTO areaVersionDTO, Long areaID) {
        List<BuildingDTO> newBuildings = validateBuildings(areaVersionDTO.buildings(), areaID);
        List<RoadDTO> newRoads = validateRoads(areaVersionDTO.roads(), areaVersionDTO.buildings(), areaID);
        boolean valid = versionValid;
        versionValid = true;

        return new AreaVersionDTO(areaVersionDTO.areaId(), areaVersionDTO.id(), areaVersionDTO.usr(), areaVersionDTO.locked(),
                areaVersionDTO.created(), areaVersionDTO.edited(), areaVersionDTO.label(),
                newBuildings, newRoads, valid);
    }

    private static List<BuildingDTO> validateBuildings(List<BuildingDTO> buildings, Long areaID) {
        List<BuildingDTO> newBuildings = new ArrayList<>();
        for (BuildingDTO buildingDTO : buildings) {
            PointDTO location = buildingDTO.location();
            Boolean valid = pointInArea(location, areaID) &&
                    pointInArea(new PointDTO(0L, location.x() + BUILDING_WIDTH, location.y()), areaID) &&
                    pointInArea(new PointDTO(0L, location.x(), location.y() + BUILDING_WIDTH), areaID) &&
                    pointInArea(new PointDTO(0L, location.x() + BUILDING_WIDTH, location.y() + BUILDING_WIDTH), areaID);

            if (!valid)
                versionValid = false;

            newBuildings.add(new BuildingDTO(buildingDTO.id(), buildingDTO.location(),
                    buildingDTO.inFlow(), buildingDTO.outFlow(), buildingDTO.residents(),
                    buildingDTO.type(), buildingDTO.label(), buildingDTO.parking(), valid));
        }
        return newBuildings;
    }

    private static List<RoadDTO> validateRoads(List<RoadDTO> roads, List<BuildingDTO> buildings, Long areaID) {
        List<RoadDTO> newRoads = new ArrayList<>();

        HashMap<PointDTO, Integer> map = new HashMap<>();
        for (RoadDTO roadDTO : roads) {
            map.put(roadDTO.start(), 0);
            map.put(roadDTO.end(), 0);
        }

        for (RoadDTO roadDTO : roads) {
            map.put(roadDTO.start(), map.get(roadDTO.start()) + 1);
            map.put(roadDTO.end(), map.get(roadDTO.end()) + 1);
        }
        List<PointDTO> singlePoints = new ArrayList<>();

        for (Map.Entry<PointDTO, Integer> pair : map.entrySet())
            if (pair.getValue() == 1)
                singlePoints.add(pair.getKey());

        List<PointDTO> troublePoints = new ArrayList<>();
        for (BuildingDTO building : buildings) {
            for (PointDTO point : singlePoints) {
                if (!isInRadius(new PointDTO(0L, building.location().x() + (float) GlobalSettings.buildingWidth / 2,
                        building.location().y() + (float) GlobalSettings.buildingWidth / 2), point))
                    troublePoints.add(point);
            }
        }

        for (RoadDTO roadDTO : roads) {
            if (!pointInArea(roadDTO.end(), areaID) || !pointInArea(roadDTO.start(), areaID)) {
                RoadDTO newRoadDTO = new RoadDTO(roadDTO.id(), roadDTO.start(), roadDTO.end(),
                        roadDTO.forward(), roadDTO.reverse(), false);
                newRoads.add(newRoadDTO);
            } else if (troublePoints.contains(roadDTO.start()) || troublePoints.contains(roadDTO.end())) {
                RoadDTO newRoadDTO = new RoadDTO(roadDTO.id(), roadDTO.start(), roadDTO.end(),
                        roadDTO.forward(), roadDTO.reverse(), false);
                newRoads.add(newRoadDTO);
            } else {
                RoadDTO newRoadDTO = new RoadDTO(roadDTO.id(), roadDTO.start(), roadDTO.end(),
                        roadDTO.forward(), roadDTO.reverse(), true);
                newRoads.add(newRoadDTO);
            }
        }
        return newRoads;
    }

    private static boolean pointInArea(PointDTO point, Long areaID) {
        AreasPlacement placement = new AreasPlacement();
        List<Elem> elems = placement.getElems();
        for (Elem elem : elems) {
            if (elem.getAreaVersionId().equals(areaID)) {
                List<Long> indexes = elem.getCellIds();
                for (Long index : indexes) {
                    int i = (int) (index / CELLS_COUNT);
                    int j = (int) (index % CELLS_COUNT);
                    PointDTO leftTopPointCell = new PointDTO(0L, (double) (j * CELL_SIZE),
                            (double) (i * CELL_SIZE));
                    PointDTO rightBottomPointCell = new PointDTO(0L, (double) ((j + 1) * CELL_SIZE),
                            (double) ((i + 1) * CELL_SIZE));
                    boolean isInCell = (point.x() >= leftTopPointCell.x()) &&
                            (point.y() >= leftTopPointCell.y()) &&
                            (point.x() <= rightBottomPointCell.x()) &&
                            (point.y() <= rightBottomPointCell.y());
                    if (isInCell)
                        return true;
                }
                return false;
            }
        }
        return false;
    }

    private static boolean isInRadius(PointDTO centerPoint, PointDTO pointDTO) {
        return (Math.pow(centerPoint.x() - pointDTO.x(), 2) +
                Math.pow(centerPoint.y() - pointDTO.y(), 2)) <=
                Math.pow(GlobalSettings.buildingConnectionRadius, 2);
    }
}
