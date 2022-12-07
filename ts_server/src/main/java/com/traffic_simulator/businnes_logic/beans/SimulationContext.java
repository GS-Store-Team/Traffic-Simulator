package com.traffic_simulator.businnes_logic.beans;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.RoadDTO;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class SimulationContext {

    private static long buildingId = 0;
    private static long roadId = 0;
    private List<BuildingDTO> buildingDTOList = new ArrayList<>();
    private List<RoadDTO> roadDTOList = new ArrayList<>();

    public long addBuildingDTO(BuildingDTO buildingDTO){
        buildingDTO.setId(roadId++);
        buildingDTOList.add(buildingDTO);
        return buildingDTO.getId();
    }

    public long addRoadDTO(RoadDTO roadDTO){
        roadDTO.setId(roadId++);
        roadDTOList.add(roadDTO);
        return roadDTO.getId();
    }

    public RoadDTO getRoadDTOById(long id){
        return getRoadDTOList().stream().filter(r -> r.getId()==id).findFirst().orElse(null);
    }

    public BuildingDTO getBuildingDTOById(long id){
        return buildingDTOList.stream().filter(b -> b.getId()==id).findFirst().orElse(null);
    }

    public boolean deleteRoadDTOById(long id){
        RoadDTO roadDTO = getRoadDTOList().stream().filter(r -> r.getId()==id).findFirst().orElse(null);
        if(roadDTO == null) return false;
        roadDTOList.remove(roadDTO);
        return true;
    }

    public boolean deleteBuildingDTOById(long id){
        BuildingDTO buildingDTO = buildingDTOList.stream().filter(b -> b.getId()==id).findFirst().orElse(null);
        if(buildingDTO == null) return false;
        buildingDTOList.remove(buildingDTO);
        return true;
    }
}
