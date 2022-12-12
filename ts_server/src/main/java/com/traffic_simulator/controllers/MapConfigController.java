package com.traffic_simulator.controllers;

import com.traffic_simulator.businnes_logic.beans.SimulationContext;
import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.RoadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapConfigController {
    private final SimulationContext simulationContext;

    @GetMapping("/roads/{id}")
    public ResponseEntity<RoadDTO> getRoad(@PathVariable("id") long id){
        RoadDTO roadDTO = simulationContext.getRoadDTOById(id);
        return roadDTO != null?
                ResponseEntity.ok(roadDTO):
                ResponseEntity.notFound().build();
    }

    @PostMapping("/roads")
    public ResponseEntity<?> addNewRoad(@RequestBody RoadDTO roadDTO){
        System.out.println(roadDTO);
        long id = simulationContext.addRoadDTO(roadDTO);
        return ResponseEntity.ok(id);
    }

    @PatchMapping("/roads/{id}")
    public ResponseEntity<?> editRoad(@PathVariable("id") long id,
                                      RoadDTO roadDTO){
        System.out.println(roadDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/roads/{id}")
    public ResponseEntity<?> deleteRoad(@PathVariable("id") long id){
        System.out.println(id);
        boolean status = simulationContext.deleteRoadDTOById(id);
        return status?
                ResponseEntity.ok().build():
                ResponseEntity.notFound().build();
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<BuildingDTO> getBuilding(@PathVariable("id") long id){
        System.out.println(id);
        BuildingDTO buildingDTO = simulationContext.getBuildingDTOById(id);
        return buildingDTO != null?
                ResponseEntity.ok(buildingDTO):
                ResponseEntity.notFound().build();
    }

    @PostMapping("/buildings")
    public ResponseEntity<?> addNewBuilding(@RequestBody BuildingDTO buildingDTO){
        System.out.println(buildingDTO);
        long id = simulationContext.addBuildingDTO(buildingDTO);
        return ResponseEntity.ok(id);
    }

    @PatchMapping("/buildings/{id}")
    public ResponseEntity<?> editRoad(@PathVariable("id") long id,
                                      BuildingDTO buildingDTO){
        System.out.println(buildingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/buildings/{id}")
    public ResponseEntity<?> deleteBuilding(@PathVariable("id") long id){
        System.out.println(id);
        boolean state = simulationContext.deleteBuildingDTOById(id);
        return state?
                ResponseEntity.ok().build():
                ResponseEntity.notFound().build();
    }
}