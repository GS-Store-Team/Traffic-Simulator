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
import java.util.Objects;

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

    @PostMapping("/{areaVersion}/roads")
    public FullMapDTO addRoad(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody RoadDTO roadDTO) {
        areaVersionService.findById(areaVersionId).getRoads().add(roadDTO);
        return areaVersionService.getState();
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
        RoadDTO roadDTO = areaVersionService.getState().areas().get(0).versions().stream().filter(a -> Objects.equals(a.id(), areaVersionId)).findFirst().get().roads().stream().filter(r -> r.id().equals(roadId)).findFirst().get();
        areaVersionService.getState().areas().get(0).versions().stream().filter(a -> Objects.equals(a.id(), areaVersionId)).findFirst().get().roads().remove(roadDTO);
        return areaVersionService.getState();
    }

    @PostMapping("/{areaVersion}/buildings")
    public FullMapDTO addBuilding(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody BuildingDTO buildingDTO) {
        areaVersionService.getState().areas().get(0).versions().stream().filter(a -> Objects.equals(a.id(), areaVersionId)).findFirst().get().buildings().add(buildingDTO);
        return areaVersionService.getState();
    }

    @PatchMapping("/{areaVersion}/buildings")
    public FullMapDTO editBuilding(
            @PathVariable("areaVersion") Long areaVersionId,
            @RequestBody BuildingDTO buildingDTO) {
        return null;
    }

    @DeleteMapping("/{areaVersion}/buildings/{id}")
    public FullMapDTO deleteBuilding(
            @PathVariable("areaVersion") Long areaVersionId,
            @PathVariable("id") Long buildingId) {
        BuildingDTO buildingDTO = areaVersionService.getState().areas().get(0).versions().stream().filter(a -> Objects.equals(a.id(), areaVersionId)).findFirst().get().buildings().stream().filter(r -> r.id().equals(buildingId)).findFirst().get();
        areaVersionService.getState().areas().get(0).versions().stream().filter(a -> Objects.equals(a.id(), areaVersionId)).findFirst().get().buildings().remove(buildingDTO);
        return areaVersionService.getState();
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