package com.traffic_simulator.controllers;

import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.RoadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
public class MapConfigController {

    @GetMapping("/roads/{id}")
    public ResponseEntity<BuildingDTO> getRoad(@PathVariable("id") long id){
        System.out.println(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/roads")
    public ResponseEntity<?> addNewRoad(@RequestBody RoadDTO roadDTO){
        System.out.println(roadDTO);
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<BuildingDTO> getBuilding(@PathVariable("id") long id){
        System.out.println(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/buildings")
    public ResponseEntity<?> addNewBuilding(@RequestBody BuildingDTO buildingDTO){
        System.out.println(buildingDTO);
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
    }
}