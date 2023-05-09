package com.traffic_simulator.simulation.graph;

import com.traffic_simulator.dto.*;
import com.traffic_simulator.simulation.GlobalSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionValidation {
    private final AreaVersionDTO area;
    private boolean canRun = true;
    private ArrayList<Long> buildingsErrorId;
    private ArrayList<Long> roadsErrorId;

    public VersionValidation(AreaVersionDTO areaVersionDTO) {
        this.area = areaVersionDTO;
        validateRoads();
    }

    private void validateRoads(){
        HashMap<PointDTO, Integer> map = new HashMap<>();
        for (RoadDTO road : area.roads()) {
            map.put(road.start(), map.get(road.start()) + 1);
            map.put(road.end(), map.get(road.end()) + 1);
        }
        List<PointDTO> singlePoints = new ArrayList<>();

        for (Map.Entry<PointDTO, Integer> pair : map.entrySet())
            if (pair.getValue() == 1)
                singlePoints.add(pair.getKey());

        for (BuildingDTO building : area.buildings()){
            for (PointDTO point : singlePoints)
                if (isInRadius(new PointDTO(1L, building.location().x() + (float) GlobalSettings.buildingWidth / 2,
                        building.location().y() + (float) GlobalSettings.buildingWidth / 2), point))
                    continue;
            canRun = false;
            break;
        }
    }

    private boolean isCanRun(){
        return canRun;
    }

    private boolean isInRadius(PointDTO centerPoint, PointDTO pointDTO) {
        return (Math.pow(centerPoint.x() - pointDTO.x(), 2) +
                Math.pow(centerPoint.y() - pointDTO.y(), 2)) <=
                Math.pow(GlobalSettings.buildingConnectionRadius, 2);
    }

    public ArrayList<Long> getRoadsErrorId(){
        return roadsErrorId;
    }

    public ArrayList<Long> getBuildingsErrorId(){
        return buildingsErrorId;
    }
}
