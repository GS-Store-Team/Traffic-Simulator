package com.traffic_simulator.simulation.models;

import lombok.Data;

import java.util.List;

@Data
public class Lane  {
    private List<Cell> cells;

    public Lane(Coordinate start, Coordinate end) {

    }
}