package com.traffic_simulator.controllers;

import com.traffic_simulator.dto.BuildingDTO;
import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.dto.ParkingDTO;
import com.traffic_simulator.dto.RoadDTO;
import com.traffic_simulator.models.areasConfig.AreasPlacement;
import com.traffic_simulator.services.AreaVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapConfigController {
    private final AreaVersionService areaVersionService;

    @GetMapping("/areas")
    public AreasPlacement getAreas() {
        return new AreasPlacement();
    }

    @GetMapping
    public FullMapDTO getMap() {
        return new FullMapDTO(1L, new ArrayList<>());
    }

    @PostMapping("/{areaVersion}/roads")
    public FullMapDTO addRoad(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody RoadDTO roadDTO) {

        return null;
    }

    @PatchMapping("/{areaVersion}/roads")
    public FullMapDTO editRoad(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody RoadDTO roadDTO) {
        return null;
    }

    @DeleteMapping("/{areaVersion}/roads/{id}")
    public FullMapDTO deleteRoad(
            @PathVariable("areaVersion") Long areaVersionId,
            @PathVariable("id") Long roadId) {
        return null;
    }

    @PostMapping("/{areaVersion}/buildings")
    public FullMapDTO addBuilding(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody BuildingDTO buildingDTO) {
        return null;
    }

    @PatchMapping("/{areaVersion}/buildings")
    public FullMapDTO editRoad(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody BuildingDTO buildingDTO) {
        return null;
    }

    @DeleteMapping("/{areaVersion}/buildings/{id}")
    public FullMapDTO deleteBuilding(
            @PathVariable("areaVersion") Long areaVersionId,
            @PathVariable("id") Long buildingId) {
        return null;
    }

    @PostMapping("/{areaVersion}/parking")
    public FullMapDTO addParking(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody ParkingDTO parkingDTO) {
        return null;
    }

    @PatchMapping("/{areaVersion}/parking")
    public FullMapDTO editParking(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody ParkingDTO parkingDTO) {
        return null;
    }

    @DeleteMapping("/{areaVersion}/parking/{id}")
    public FullMapDTO deleteParking(
            @PathVariable("areaVersion") Long areaVersionId,
            @PathVariable("id") Long parkingZoneId) {
        return null;
    }
}