package com.trafficsimulator.controllers;

import com.trafficsimulator.simulation.models.Road;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
public class MapConfigController {

    @GetMapping("/road/{id}")
    public Road getRoad(@PathVariable("id") long id){
        return new Road();
    }

    @PostMapping("/road")
    public ResponseEntity<?> addNewRoad(Road road){
        System.out.println(road);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/road/{id}")
    public ResponseEntity<?> editRoad(@PathVariable("id") long id,
                                      Road road){
        System.out.println(road);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/road/{id}")
    public ResponseEntity<?> deleteRoad(@PathVariable("id") long id){
        System.out.println(id);
        return ResponseEntity.status(200).build();
    }
}
