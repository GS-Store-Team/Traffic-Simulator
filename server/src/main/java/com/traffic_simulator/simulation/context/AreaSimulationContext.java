
package com.traffic_simulator.simulation.context;

import com.traffic_simulator.dto.AreaVersionDTO;
import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.dto.UserDTO;
import com.traffic_simulator.models.AreaVersion;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class AreaSimulationContext {

    private static long buildingId = 0;
    private static long roadId = 0;

    private AreaVersion areaVersion;
    private List<BuildingDTO> buildingDTOList = new ArrayList<>();
    private List<RoadDTO> roadDTOList = new ArrayList<>();

    public AreaSimulationContext() {
        areaVersion = new AreaVersion();
    }
    /*public AreaVersionDTO areaVersionDTO(Long id, UserDTO userDTO) {
        return new AreaVersionDTO(id, userDTO, true,)
    }
    public AreaVersion areaVersion() {

    }*/

    public long addBuildingDTO(BuildingDTO buildingDTO){
        BuildingDTO localBuildingDTO = new BuildingDTO(buildingId++, buildingDTO);
        buildingDTOList.add(localBuildingDTO);
        return localBuildingDTO.id();
    }

    public long addRoadDTO(RoadDTO roadDTO){
        RoadDTO localRoadDTO = new RoadDTO(roadId++, roadDTO);
        roadDTOList.add(localRoadDTO);
        return localRoadDTO.id();
    }

    public RoadDTO getRoadDTOById(long id){
        return getRoadDTOList().stream().filter(r -> r.id()==id).findFirst().orElse(null);
    }

    public BuildingDTO getBuildingDTOById(long id){
        return buildingDTOList.stream().filter(b -> b.id()==id).findFirst().orElse(null);
    }

    public boolean deleteRoadDTOById(long id){
        RoadDTO roadDTO = getRoadDTOList().stream().filter(r -> r.id()==id).findFirst().orElse(null);
        if(roadDTO == null) return false;
        roadDTOList.remove(roadDTO);
        return true;
    }

    public boolean deleteBuildingDTOById(long id){
        BuildingDTO buildingDTO = buildingDTOList.stream().filter(b -> b.id()==id).findFirst().orElse(null);
        if(buildingDTO == null) return false;
        buildingDTOList.remove(buildingDTO);
        return true;
    }
}

