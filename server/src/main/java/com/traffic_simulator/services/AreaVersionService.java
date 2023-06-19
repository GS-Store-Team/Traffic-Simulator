package com.traffic_simulator.services;

import com.traffic_simulator.dto.AreaDTO;
import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.models.*;
import com.traffic_simulator.repository.AreaRepository;
import com.traffic_simulator.repository.AreaVersionRepository;
import com.traffic_simulator.repository.BuildingRepository;
import com.traffic_simulator.repository.RoadRepository;
import com.traffic_simulator.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AreaVersionService {
    private final AreaRepository areaRepository;
    private final AreaVersionRepository areaVersionRepository;
    private final RoadRepository roadRepository;
    private final BuildingRepository buildingRepository;

    //delete this
    private final Usr usr = new Usr(0L, "admin", "1234", null);

    public FullMapDTO getState(){
        List<Area> areas = areaRepository.findAll();
        return new FullMapDTO(
                0L,
                areas.stream().map(a -> new AreaDTO(a, a.getVersions(), true)).collect(Collectors.toList())
        );
    }

    public FullMapDTO addAreaVersion(Long areaId, String versionName){
        Area area = areaRepository.getReferenceById(areaId);
        var areaVersion = new AreaVersion();
        areaVersion.setLabel(versionName);
        areaVersion.setUsr(usr);
        var timestamp = new Timestamp(System.currentTimeMillis());
        areaVersion.setCreated(timestamp);
        areaVersion.setEdited(timestamp);
        areaVersion.setArea(area);
        areaVersion.setBuildings(Collections.emptyList());
        areaVersion.setRoads(Collections.emptyList());
        areaVersion.setLocked(true);

        areaVersionRepository.save(areaVersion);

        return getState();
    }

    public FullMapDTO deleteAreaVersion(Long areaVersionId){
        areaVersionRepository.deleteById(areaVersionId);
        return getState();
    }

    public FullMapDTO configureLock(Long areaVersionId, Boolean locked){
        var version = areaVersionRepository.findById(areaVersionId).get();
        version.setLocked(locked);
        areaVersionRepository.save(version);
        return getState();
    }

    public FullMapDTO road(Long areaVersionId, RoadDTO roadDTO){
        AreaVersion areaVersion = areaVersionRepository.getReferenceById(areaVersionId);
        areaVersion.setEdited(new Timestamp(System.currentTimeMillis()));
        areaVersionRepository.save(areaVersion);

        Road road = new Road();

        if(roadDTO.id() != null){
            road = roadRepository.findById(roadDTO.id()).get();
            Point start = road.getStart();
            start.setX(roadDTO.start().x());
            start.setY(roadDTO.start().y());

            Point end = road.getEnd();
            end.setX(roadDTO.end().x());
            end.setY(roadDTO.end().y());
        }
        else {
            road.setStart(new Point(roadDTO.start().x(), roadDTO.start().y()));
            road.setEnd(new Point(roadDTO.end().x(), roadDTO.end().y()));
        }
        road.setAreaVersion(areaVersion);
        road.setReverse(roadDTO.reverse());
        road.setForward(roadDTO.forward());

        roadRepository.save(road);
        return getState();
    }

    public FullMapDTO building(Long areaVersionId, BuildingDTO buildingDTO){
        AreaVersion areaVersion = areaVersionRepository.getReferenceById(areaVersionId);
        areaVersion.setEdited(new Timestamp(System.currentTimeMillis()));
        areaVersionRepository.save(areaVersion);

        Building building = new Building();

        if(buildingDTO.id() != null){
            building = buildingRepository.findById(buildingDTO.id()).get();
            Point location = building.getLocation();
            location.setX(buildingDTO.location().x());
            location.setY(buildingDTO.location().y());
        }else {
            building.setLocation(new Point(buildingDTO.location().x(), buildingDTO.location().y()));
        }
        building.setAreaVersion(areaVersion);
        building.setInFlow(buildingDTO.inFlow());
        building.setOutFlow(buildingDTO.outFlow());
        building.setType(buildingDTO.type());
        building.setLabel(buildingDTO.label());
        building.setResidents(buildingDTO.residents());

        buildingRepository.save(building);
        return getState();
    }

    public FullMapDTO removeRoad(Long roadId){
        roadRepository.deleteById(roadId);
        return getState();
    }
    public FullMapDTO removeBuilding(Long buildingId){
        buildingRepository.deleteById(buildingId);
        return getState();
    }
}
