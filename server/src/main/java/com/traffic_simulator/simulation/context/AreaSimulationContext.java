
package com.traffic_simulator.simulation.context;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.RoadDTO;
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
    private static long pointId = 0;

    private AreaVersion areaVersion;
    private List<BuildingDTO> buildingDTOList = new ArrayList<>();
    private List<RoadDTO> roadDTOList = new ArrayList<>();

    public AreaSimulationContext() {
        areaVersion = new AreaVersion();
        constructExample();
    }
    /*public AreaVersionDTO areaVersionDTO(Long id, UserDTO userDTO) {
        return new AreaVersionDTO(id, userDTO, true,)
    }
    public AreaVersion areaVersion() {

    }*/

    private void constructExample() {
        /*roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 130,70), new PointDTO(pointId++, 290, 70), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 130,70), new PointDTO(pointId++, 130, 200), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 290,70), new PointDTO(pointId++, 290, 70), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 130,200), new PointDTO(pointId++, 290, 200), 1, 1,true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 290,70), new PointDTO(pointId++, 460, 70), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 290,70), new PointDTO(pointId++, 290, 200), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 290,200), new PointDTO(pointId++, 410, 200), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 410,200), new PointDTO(pointId++, 460, 70), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 290,200), new PointDTO(pointId++, 290, 330), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 130,200), new PointDTO(pointId++, 50, 460), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 50,460), new PointDTO(pointId++, 290, 330), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 290,330), new PointDTO(pointId++, 460, 330), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 460,70), new PointDTO(pointId++, 730, 220), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 460,330), new PointDTO(pointId++, 730, 220), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 460,330), new PointDTO(pointId++, 750, 370), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 730,220), new PointDTO(pointId++, 750, 370), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 730,220), new PointDTO(pointId++, 930, 150), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 750,370), new PointDTO(pointId++, 870, 640), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 720,600), new PointDTO(pointId++, 750, 370), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 720,600), new PointDTO(pointId++, 870, 640), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 50,460), new PointDTO(pointId++, 110, 560), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 110,560), new PointDTO(pointId++, 210, 560), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 720,600), new PointDTO(pointId++, 210, 560), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 110,560), new PointDTO(pointId++, 200, 650), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 210,560), new PointDTO(pointId++, 200, 650), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 650,650), new PointDTO(pointId++, 870, 640), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 720,600), new PointDTO(pointId++, 650, 650), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 200,650), new PointDTO(pointId++, 340, 650), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 340,650), new PointDTO(pointId++, 400, 650), 1, 1, true));
        roadDTOList.add(new RoadDTO(roadId++, new PointDTO(pointId++, 400,650), new PointDTO(pointId++, 650, 650), 1, 1, true));

        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 140, 10), 0.1, 0.1, 5, BuildingType.LIVING, "L1", new ParkingDTO(buildingId, 10, new Point(pointId++, 140D, 10D), true), true));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 140, 80), 0.1, 0.1, 5, BuildingType.LIVING, "L2", new ParkingDTO(buildingId, 10, new Point(pointId++, 140D, 80D), true), true));
        //buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 60, 60)));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 920, 160), 0.1, 0.1, 5, BuildingType.LIVING, "L3", new ParkingDTO(buildingId, 10, new Point(pointId++, 920D, 160D), true), true));
        //buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 920, 80)));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 290, 660), 0.1, 0.1, 5, BuildingType.LIVING, "L4", new ParkingDTO(buildingId, 10, new Point(pointId++, 290D, 660D), true), true));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 350, 660), 0.1, 0.1, 5, BuildingType.LIVING, "L5", new ParkingDTO(buildingId, 10, new Point(pointId++, 350D, 660D), true), true));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 410, 660), 0.1, 0.1, 5, BuildingType.WORKING, "W1", new ParkingDTO(buildingId, 10, new Point(pointId++, 410D, 660D), true), true));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 290, 590), 0.1, 0.1, 5, BuildingType.WORKING, "W2", new ParkingDTO(buildingId, 10, new Point(pointId++, 290D, 590D), true), true));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 350, 590), 0.1, 0.1, 5, BuildingType.WORKING, "W3", new ParkingDTO(buildingId, 10, new Point(pointId++, 350D, 590D), true), true));
        buildingDTOList.add(new BuildingDTO(buildingId++, new PointDTO(pointId++, 410, 590), 0.1, 0.1, 5, BuildingType.WORKING, "W4", new ParkingDTO(buildingId, 10, new Point(pointId++, 410D, 590D), true), true));
        //buildingDTOList.add(new BuildingDTO(cnt++, new PointDTO(pointId++, 875, 655)));

        /*addRoadDTO(new RoadDTO(cnt++, new PointDTO(pointId++, 100, 100), new PointDTO(pointId++, 200, 200), 1, 1, null, null));
        addRoadDTO(new RoadDTO(cnt++, new PointDTO(pointId++, 200, 200), new PointDTO(pointId++, 300, 300), 1, 1, null, null));
        //addRoadDTO(new RoadDTO(cnt++, new PointDTO(pointId++, 200,200), new PointDTO(pointId++, 400, 400), 1, 1, null, null));
        //addRoadDTO(new RoadDTO(cnt++, new PointDTO(pointId++, 300,300), new PointDTO(pointId++, 400, 400), 1, 1, null, null));
        //addRoadDTO(new RoadDTO(cnt++, new PointDTO(pointId++, 300,300), new PointDTO(pointId++, 500, 500), 1, 1, null, null));

        addBuildingDTO(new BuildingDTO(cnt++, new PointDTO(pointId++, 120, 100), BuildingType.LIVING, 1, 10));
        addBuildingDTO(new BuildingDTO(cnt++, new PointDTO(pointId++, 80, 100), BuildingType.LIVING, 1, 10));
        addBuildingDTO(new BuildingDTO(cnt++, new PointDTO(pointId++, 310, 300), BuildingType.WORK, 1, 10));
        addBuildingDTO(new BuildingDTO(cnt++, new PointDTO(pointId++, 290, 300), BuildingType.WORK, 1, 10));
        //addBuildingDTO(new BuildingDTO(cnt++, new PointDTO(pointId++, 510, 500), BuildingType.WORK, 1, 10));
        //addBuildingDTO(new BuildingDTO(cnt++, new PointDTO(pointId++, 490, 500), BuildingType.WORK, 1, 10));*/
    }

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

