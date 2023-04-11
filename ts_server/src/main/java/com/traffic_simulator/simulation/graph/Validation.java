package com.traffic_simulator.simulation.graph;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.PointDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.exceptions.InvalidMapException;
import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.context.SimulationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validation {
    private SimulationContext simulationContext;
    private ArrayList<Long> buildingsErrorId;
    private ArrayList<Long> roadsErrorId;

    public Validation(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
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
        for (BuildingDTO buildingDTO : simulationContext.getBuildingDTOList()) {
            boolean check = false;
            for (RoadDTO roadDTO : simulationContext.getRoadDTOList()) {
                if (roadInBuildingArea(roadDTO, buildingDTO, false)) {
                    check = true;
                }
                if (roadInBuildingArea(roadDTO, buildingDTO, true)) {
                    check = true;
                }
                if (isInRadius(new PointDTO(buildingDTO.getLocation().getX() + (float) GlobalSettings.buildingWidth / 2,
                        buildingDTO.getLocation().getY() + (float) GlobalSettings.buildingWidth / 2), roadDTO.getStart()))
                    map.put(buildingDTO.getLocation(), roadDTO.getStart());

                if (isInRadius(new PointDTO(buildingDTO.getLocation().getX() + (float) GlobalSettings.buildingWidth / 2,
                        buildingDTO.getLocation().getY() + (float) GlobalSettings.buildingWidth / 2), roadDTO.getEnd()))
                    map.put(buildingDTO.getLocation(), roadDTO.getEnd());
            }
            if (!check)
                buildingsErrorId.add(buildingDTO.getId());
        }
    }

    private Map<PointDTO, PointDTO> map = new HashMap<>();

    public Map<PointDTO, PointDTO> getMap() {
        return map;
    }


    private void validateRoads() {
        for (RoadDTO roadDTO : simulationContext.getRoadDTOList()) {
            int currentConnections = 0;
            boolean checkWithOnePoint = false;
            boolean startPoint = false;
            for (RoadDTO roadDTO1 : simulationContext.getRoadDTOList()) {
                if (roadDTO.getId() == roadDTO1.getId())
                    continue;

                //checking start point connections
                if (roadDTO.getStart().equals(roadDTO1.getStart()) || roadDTO.getStart().equals(roadDTO1.getEnd())) {
                    currentConnections += 1;
                    startPoint = true;
                }

                //checking end point connections
                else if (roadDTO.getEnd().equals(roadDTO1.getStart()) || roadDTO.getEnd().equals(roadDTO1.getEnd()))
                    currentConnections += 1;
            }
            if (currentConnections == 0)
                roadsErrorId.add(roadDTO.getId());

            if (currentConnections == 1) {
                for (BuildingDTO buildingDTO : simulationContext.getBuildingDTOList())
                    if (roadInBuildingArea(roadDTO, buildingDTO, startPoint)) {
                        checkWithOnePoint = true;
                        break;
                    }

                if (!checkWithOnePoint)
                    roadsErrorId.add(roadDTO.getId());
            }
        }
    }

    private boolean roadInBuildingArea(RoadDTO roadDTO, BuildingDTO buildingDTO, boolean startPoint) {
        if (startPoint) {
            return isInRadius(new PointDTO(buildingDTO.getLocation().getX() + (float) GlobalSettings.buildingWidth / 2,
                    buildingDTO.getLocation().getY() + (float) GlobalSettings.buildingWidth / 2), roadDTO.getEnd());
        } else {
            return isInRadius(new PointDTO(buildingDTO.getLocation().getX() + (float) GlobalSettings.buildingWidth / 2,
                    buildingDTO.getLocation().getY() + (float) GlobalSettings.buildingWidth / 2), roadDTO.getStart());
        }
    }

    private boolean isInRadius(PointDTO circlePoint, PointDTO pointDTO) {
        return (Math.pow(circlePoint.getX() - pointDTO.getX(), 2) +
                Math.pow(circlePoint.getY() - pointDTO.getY(), 2)) <=
                Math.pow(GlobalSettings.buildingConnectionRadius, 2);
    }


}
