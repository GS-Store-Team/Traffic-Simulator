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

@CrossOrigin(origins = {"http://localhost:3000"})
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
        return areaVersionService.getState();
    }

    @PostMapping("/areas/{areaId}/versions")
    public FullMapDTO addAreaVersion(
            @PathVariable("areaId") Long areaId,
            @RequestBody String versionName) {
        return areaVersionService.addAreaVersion(areaId, versionName);
    }

    @DeleteMapping("/areas/versions/{versionId}")
    public FullMapDTO removeAreaVersion(@PathVariable("versionId") Long versionId) {
        return areaVersionService.deleteAreaVersion(versionId);
    }

    @PostMapping("/{areaVersion}/roads")
    public FullMapDTO addRoad(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody RoadDTO roadDTO) {
        return areaVersionService.road(areaVersionId, roadDTO);
    }

    @DeleteMapping("/roads/{id}")
    public FullMapDTO deleteRoad(@PathVariable("id") Long roadId) {
        return areaVersionService.removeRoad(roadId);
    }

    @PostMapping("/{areaVersion}/buildings")
    public FullMapDTO addBuilding(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody BuildingDTO buildingDTO) {
        return areaVersionService.building(areaVersionId, buildingDTO);
    }

    @DeleteMapping("/buildings/{id}")
    public FullMapDTO deleteBuilding(@PathVariable("id") Long buildingId) {
        return areaVersionService.removeBuilding(buildingId);
    }

    @PostMapping("/{areaVersion}/parking")
    public FullMapDTO addParking(
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