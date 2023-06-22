package com.traffic_simulator.simulation.models;

import com.traffic_simulator.utils.Utils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class Lane {
    private List<Cell> cells = new ArrayList<>();

    private static final double CELL_GAP = 1;

    public Lane(Coordinate start, Coordinate end) {

        double length = Math.sqrt(Math.pow(end.x() - start.x(), 2) + Math.pow(end.y() - start.y(), 2));
        int cellAmount = (int) (length / CELL_GAP + 1);

        double sin = Utils.computeSin(start, end);
        double cos = Utils.computeCos(start, end);

        IntStream.range(0, cellAmount)
                .boxed()
                .map(i -> new Cell(new Coordinate(start.x() + CELL_GAP * sin * i, start.y() + CELL_GAP * cos * i)))
                .forEach(cell -> cells.add(cell));
    }

    public boolean canPush() {
        return cells.size() > 0 && cells.get(0).getCar() == null;
    }

    public void push(Car car) {
        if (canPush()) {
            cells.get(0).setCar(car);
        }
    }

    public void update() {
        if (cells.get(cells.size() - 1) != null) {
            cells.get(cells.size() - 1).setCar(null);
        }
        for (int i = cells.size() - 2; i >= 0; i--) {
            if (cells.get(i).getCar() != null && cells.get(i + 1).getCar() == null) {
                cells.get(i + 1).setCar(cells.get(i).getCar());
                cells.get(i).setCar(null);
            }
        }
    }
}