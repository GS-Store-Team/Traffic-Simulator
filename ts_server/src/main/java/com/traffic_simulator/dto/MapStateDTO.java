package com.traffic_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class MapStateDTO {
    private List<BuildingDTO> buildings;
    private List<RoadDTO> roads;
    private List<RoadSignDTO> signs;
    private List<AttachmentPointDTO> crossroads;

    private void addBuilding(BuildingDTO buildingDTO){
        buildings.add(buildingDTO);
    }

    private void addRoads(RoadDTO roadDTO){
        roads.add(roadDTO);
    }

    private void addSign(RoadSignDTO roadSignDTO){
        signs.add(roadSignDTO);
    }

    private void addCrossroad(AttachmentPointDTO attachmentPointDTO){
        crossroads.add(attachmentPointDTO);
    }
}
