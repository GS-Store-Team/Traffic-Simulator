
package com.traffic_simulator.simulation.graph;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.PointDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.context.AreaSimulationContext;

import java.util.*;

public class Validation {
    private AreaSimulationContext areaSimulationContext;
    private ArrayList<Long> buildingsErrorId;
    private ArrayList<Long> roadsErrorId;

    public Validation(AreaSimulationContext areaSimulationContext) {
        this.areaSimulationContext = areaSimulationContext;
        roadsErrorId = new ArrayList<>();
        buildingsErrorId = new ArrayList<>();
    }

    public void checkErrors() throws InvalidMapException {
        validateRoads();
        validateBuildings();
        if ((buildingsErrorId.size() + roadsErrorId.size()) > 0) {
            throw new InvalidMapException("invalid map exception", null);
        }
    }

    public Map<String, List<Long>> getErrors() {
        if ((buildingsErrorId.size() + roadsErrorId.size()) > 0)
            return null;

        Map<String, List<Long>> errors = new HashMap<>();
        errors.put("badRoads", roadsErrorId);
        errors.put("badBuildings", buildingsErrorId);
        return errors;
    }

    private void validateBuildings() {
        for (BuildingDTO buildingDTO : areaSimulationContext.getBuildingDTOList()) {
            boolean check = false;
            for (RoadDTO roadDTO : areaSimulationContext.getRoadDTOList()) {
                if (roadInBuildingArea(roadDTO, buildingDTO, false)) {
                    check = true;
                }
                if (roadInBuildingArea(roadDTO, buildingDTO, true)) {
                    check = true;
                }
                if (isInRadius(new PointDTO(
                                -1L,
                                buildingDTO.location().x() + (double) GlobalSettings.buildingWidth / 2,
                                buildingDTO.location().y() + (double) GlobalSettings.buildingWidth / 2),
                        roadDTO.start()))
                    map.put(buildingDTO.location(), roadDTO.start());

                if (isInRadius(new PointDTO(
                                -1L,
                                buildingDTO.location().x() + (double) GlobalSettings.buildingWidth / 2,
                                buildingDTO.location().y() + (double) GlobalSettings.buildingWidth / 2),
                        roadDTO.end()))
                    map.put(buildingDTO.location(), roadDTO.end());
            }
            if (!check)
                buildingsErrorId.add(buildingDTO.id());
        }
    }

    private Map<PointDTO, PointDTO> map = new HashMap<>();

    public Map<PointDTO, PointDTO> getMap() {
        return map;
    }


    private void validateRoads() {
        for (RoadDTO roadDTO : areaSimulationContext.getRoadDTOList()) {
            int currentConnections = 0;
            boolean checkWithOnePoint = false;
            boolean startPoint = false;
            for (RoadDTO roadDTO1 : areaSimulationContext.getRoadDTOList()) {
                if (Objects.equals(roadDTO.id(), roadDTO1.id()))
                    continue;

                //checking start point connections
                if (roadDTO.start().equals(roadDTO1.start()) || roadDTO.start().equals(roadDTO1.end())) {
                    currentConnections += 1;
                    startPoint = true;
                }

                //checking end point connections
                else if (roadDTO.end().equals(roadDTO1.start()) || roadDTO.end().equals(roadDTO1.end()))
                    currentConnections += 1;
            }
            if (currentConnections == 0)
                roadsErrorId.add(roadDTO.id());

            if (currentConnections == 1) {
                for (BuildingDTO buildingDTO : areaSimulationContext.getBuildingDTOList())
                    if (roadInBuildingArea(roadDTO, buildingDTO, startPoint)) {
                        checkWithOnePoint = true;
                        break;
                    }

                if (!checkWithOnePoint)
                    roadsErrorId.add(roadDTO.id());
            }
        }
    }

    private boolean roadInBuildingArea(RoadDTO roadDTO, BuildingDTO buildingDTO, boolean startPoint) {
        if (startPoint) {
            return isInRadius(new PointDTO(
                            -1L,
                            buildingDTO.location().x() + (float) GlobalSettings.buildingWidth / 2,
                            buildingDTO.location().y() + (float) GlobalSettings.buildingWidth / 2),
                    roadDTO.end());
        } else {
            return isInRadius(new PointDTO(
                            -1L,
                            buildingDTO.location().x() + (float) GlobalSettings.buildingWidth / 2,
                            buildingDTO.location().y() + (float) GlobalSettings.buildingWidth / 2),
                    roadDTO.start());
        }
    }

    private boolean isInRadius(PointDTO circlePoint, PointDTO pointDTO) {
        return (Math.pow(circlePoint.x() - pointDTO.x(), 2) +
                Math.pow(circlePoint.y() - pointDTO.y(), 2)) <=
                Math.pow(GlobalSettings.buildingConnectionRadius, 2);
    }


}

