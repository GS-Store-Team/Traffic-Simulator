package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPath;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Navigator {
    private double acceleration;
    private double accelerationUpperLimit;
    private double accelerationLowerLimit;
    private long departureTime;
    private long workTime;
    private NodeNe currentNode;
    private NodeNe nextNode;

    private List<Cell> advance;

    private List<Road> roads;
    private Road currentRoad;
    private Coordinates currentCoordinates;
    private float currentCos;
    private float currentSin;
    private int index;

    private Car car;

    public Navigator(Car car, double accelerationUpperLimit, double accelerationLowerLimit, CarPath carPath) {
        this.acceleration = 0;
        this.accelerationUpperLimit = accelerationUpperLimit;
        this.accelerationLowerLimit = accelerationLowerLimit;
        this.car = car;
        this.currentNode = null;
        this.nextNode = null;
        this.roads = carPath.getRoads().stream().toList();
        this.advance = new ArrayList<>();
        this.departureTime = 0;
        this.workTime = 0;
        this.index = 0;
        try{
            this.currentRoad = roads.get(index);
            currentCoordinates = currentRoad.getStartCoordinate();
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("road list is null");
            //throw new Exception("empty road list");
        }
    }

    public void update() {
        moveCar();
    }

    public void reset() {

    }
    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    private void moveCar(){

        if (currentCoordinates != currentRoad.getEndCoordinate()){
            try {
                float length = lengthRoad(currentRoad.getStartCoordinate(), currentRoad.getEndCoordinate());
                currentCos = (currentRoad.getEndCoordinate().getX() - currentRoad.getStartCoordinate().getX()) / length;
                currentSin = (currentRoad.getEndCoordinate().getY() - currentRoad.getStartCoordinate().getY()) / length;
                currentCoordinates.setX(currentCoordinates.getX() + car.getCurrentVelocity() * currentCos);
                currentCoordinates.setY(currentCoordinates.getY() + car.getCurrentVelocity() * currentSin);

                if (currentCoordinates.getX() > currentRoad.getEndCoordinate().getX()) {
                    if (index == roads.size() - 1) {
                        currentCoordinates = currentRoad.getEndCoordinate();
                        return;
                    }
                    index += 1;
                    currentRoad = roads.get(index);
                }
            }
            catch (Exception e){
                System.out.println("road length is null");
                //throw new Exception("road length is null");
            }
        }
    }

    private float lengthRoad(Coordinates start, Coordinates end){
        return (float) Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }
}
