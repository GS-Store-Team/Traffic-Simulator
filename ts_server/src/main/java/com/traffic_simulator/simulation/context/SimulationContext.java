package com.traffic_simulator.simulation.context;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.PointDTO;
import com.traffic_simulator.dto.RoadDTO;
import jakarta.annotation.PostConstruct;
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


    @PostConstruct
    public void init(){
        int cnt = 0;
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(130,70), new PointDTO(290, 70), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(130,70), new PointDTO(130, 200), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(290,70), new PointDTO(290, 70), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(130,200), new PointDTO(290, 200), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(290,70), new PointDTO(460, 70), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(290,70), new PointDTO(290, 200), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(290,200), new PointDTO(410, 200), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(410,200), new PointDTO(460, 70), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(290,200), new PointDTO(290, 330), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(130,200), new PointDTO(50, 460), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(50,460), new PointDTO(290, 330), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(290,330), new PointDTO(460, 330), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(460,70), new PointDTO(730, 220), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(460,330), new PointDTO(730, 220), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(460,330), new PointDTO(750, 370), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(730,220), new PointDTO(750, 370), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(730,220), new PointDTO(930, 150), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(750,370), new PointDTO(870, 640), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(720,600), new PointDTO(750, 370), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(720,600), new PointDTO(870, 640), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(50,460), new PointDTO(110, 560), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(110,560), new PointDTO(210, 560), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(720,600), new PointDTO(210, 560), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(110,560), new PointDTO(200, 650), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(210,560), new PointDTO(200, 650), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(650,650), new PointDTO(870, 640), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(720,600), new PointDTO(650, 650), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(200,650), new PointDTO(340, 650), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(340,650), new PointDTO(400, 650), 1, 1, null, null));
        roadDTOList.add(new RoadDTO(cnt++, new PointDTO(400,650), new PointDTO(650, 650), 1, 1, null, null));

        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(140, 10)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(140, 80)));
        //buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(60, 60)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(920, 160)));
        //buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(920, 80)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(290, 660)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(350, 660)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(410, 660)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(290, 590)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(350, 590)));
        buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(410, 590)));
        //buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(875, 655)));
    }

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