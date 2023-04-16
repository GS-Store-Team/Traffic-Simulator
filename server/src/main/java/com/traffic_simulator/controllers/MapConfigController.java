package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.dto.ParkingDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.services.AreaVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapConfigController {
    private final AreaVersionService areaVersionService;

    @GetMapping
    public FullMapDTO getMap(){
        throw new RuntimeException("hi");
        //return null;
    }

    @PostMapping("/roads")
    public FullMapDTO addRoad(@RequestBody RoadDTO roadDTO){
        return null;
    }

    @PatchMapping("/roads")
    public FullMapDTO editRoad(@RequestBody RoadDTO roadDTO){
        return null;
    }

    @DeleteMapping("/roads/{id}")
    public FullMapDTO deleteRoad(@PathVariable("id") long id){
        return null;
    }

    @PostMapping("/buildings")
    public FullMapDTO addBuilding(@RequestBody BuildingDTO buildingDTO){
        return null;
    }

    @PatchMapping("/buildings")
    public FullMapDTO editRoad(@RequestBody BuildingDTO buildingDTO){
        return null;
    }

    @DeleteMapping("/buildings/{id}")
    public FullMapDTO deleteBuilding(@PathVariable("id") long id){
        return null;
    }

    @PostMapping("/parking")
    public FullMapDTO addParking(@RequestBody ParkingDTO parkingDTO){
        return null;
    }

    @PatchMapping("/parking")
    public FullMapDTO editParking(@RequestBody ParkingDTO parkingDTO){
        return null;
    }

    @DeleteMapping("/parking/{id}")
    public FullMapDTO deleteParking(@PathVariable("id") long id){
        return null;
    }
}