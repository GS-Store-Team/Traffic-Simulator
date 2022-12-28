package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.graph.graph_elements.RoadSide;
import com.traffic_simulator.simulation.models.road.Lane;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPath;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@ToString
public class Navigator {
    private double acceleration;
    private double velocity;
    private double accelerationUpperLimit;
    private double accelerationLowerLimit;
    private long departureTime;
    private long workTime;

    private List<Cell> advance;
    private NodeNe currentNode;
    private Road currentRoad;
    private final CarPath carPath;

    private Coordinates currentCoordinate;
    private float currentCos;
    private float currentSin;

    private int index;
    private boolean carRunning;
    private boolean temp = false;
    private Car car;

    public Navigator(Car car, int accelerationLowerLimit, int accelerationUpperLimit, CarPath carPath) {

        this.car = car;
        this.carPath = carPath;
        this.advance = new ArrayList<>();

        initTimers();

        this.accelerationUpperLimit = accelerationUpperLimit;
        this.accelerationLowerLimit = accelerationLowerLimit;
        initSpeedometers();

        initSelfPositioning();

        this.carRunning = false;
    }

    private void initTimers() {
        this.departureTime = 0;
        this.workTime = 0;
    }

    private void initSpeedometers() {
        this.acceleration = 0;
        this.velocity = 0;
    }

    private void initSelfPositioning() {
        try {

            this.currentNode = carPath.getNodes().pop();
            this.currentRoad = null;
            currentCoordinate = currentNode.getAttachmentPoint().getCoordinates();
        } catch (NoSuchElementException e) {
            System.out.println("No nodes in car path!");
        }

    }

    public void update(long currentTick) {
        if (currentTick >= departureTime & !carRunning) {
            carRunning = true;
        }

        if (carRunning && !temp) {
            System.out.println("Navigator #" + this.hashCode() + " is running: " + carRunning);
            temp = true;
        }

        if (carRunning) {
            updateCar();
            System.out.println("Navigator #" + this.hashCode() + " (" + currentCoordinate.getX() + ", " + currentCoordinate.getY() + ")");
        }
    }

    public void reset() {
        car.setCurrentPosition(car.getBuildingStart().getCenter());
        carRunning = false;
        initTimers();
        initSpeedometers();
        initSelfPositioning();
    }

    public Coordinates getCurrentCoordinate() {
        return currentCoordinate;
    }

    private void updateCar() {

        RoadSide roadSide = RoadSide.NONE;
        if (currentRoad == null) {
            if (currentNode.getAttachmentPoint().getConnectedBuildings().contains(car.getBuildingStart())) {
                //currentNode.getAttachmentPoint().getConnectedBuildings().get(0).getParkingZone().removeCar(car);
                car.getBuildingStart().getParkingZone().removeCar(car);
                currentCoordinate = currentNode.getAttachmentPoint().getCoordinates();
            } else if (currentNode.getAttachmentPoint().getCoordinates() == carPath.getRoads().peek().getStartCoordinate()) {
                currentCoordinate = carPath.getRoads().peek().getStartCoordinate();
                currentRoad = carPath.getRoads().pop();
                roadSide = RoadSide.RIGHT;
                currentNode = null;
            } else if (currentNode.getAttachmentPoint().getCoordinates() == carPath.getRoads().peek().getEndCoordinate()) {
                currentCoordinate = carPath.getRoads().peek().getEndCoordinate();
                currentRoad = carPath.getRoads().pop();
                roadSide = RoadSide.LEFT;
                currentNode = null;
            } else if (currentNode.getAttachmentPoint().getConnectedBuildings().contains(car.getBuildingEnd())) {
                currentCoordinate = currentNode.getAttachmentPoint().getCoordinates();
                //currentNode.getAttachmentPoint().getConnectedBuildings().get(0).getParkingZone().addCar(car);
                currentCoordinate = car.getBuildingEnd().getCenter();
                car.getBuildingEnd().getParkingZone().addCar(car);
                roadSide = RoadSide.NONE;
            }
        } else {
            if (currentCoordinate != currentRoad.getEndCoordinate()) {
                try {
                    chooseLane(roadSide);
                    moveCar(currentRoad.getStartCoordinate(), currentRoad.getEndCoordinate());
                    if (lengthRoad(currentRoad.getStartCoordinate(), currentCoordinate) >= lengthRoad(currentRoad.getStartCoordinate(), currentRoad.getEndCoordinate())) {
                        currentNode = carPath.getNodes().pop();
                        currentRoad = null;
                    }
                } catch (Exception e) {
                    System.out.println("road length is null");
                }
            }
        }

        currentRoad = carPath.getRoads().pop();
        currentNode = null;

    }

    private void chooseLane(RoadSide roadSide) {
        switch (roadSide) {
            case LEFT -> {
                Lane lane = currentRoad.getLeftLanes().stream()
                        .min((Lane lane1, Lane lane2) -> Double.compare(lane1.computeTrafficWeight(), lane1.computeTrafficWeight()))
                        .orElseThrow();
                currentCoordinate = lane.getEndCoordinates();
            }
            case RIGHT -> {
                Lane lane = currentRoad.getRightLanes().stream()
                        .min((Lane lane1, Lane lane2) -> Double.compare(lane1.computeTrafficWeight(), lane1.computeTrafficWeight()))
                        .orElseThrow();
                currentCoordinate = lane.getStartCoordinates();
            }
        }
    }

    private void moveCar(Coordinates roadStartPoint, Coordinates roadEndPoint) {
        float length = lengthRoad(roadStartPoint, roadEndPoint);
        currentCos = (roadEndPoint.getX() - roadStartPoint.getX()) / length;
        currentSin = (roadEndPoint.getY() - roadStartPoint.getY()) / length;
        currentCoordinate.setX(currentCoordinate.getX() + car.getCurrentVelocity() * currentCos);
        currentCoordinate.setY(currentCoordinate.getY() + car.getCurrentVelocity() * currentSin);
    }

    private float lengthRoad(Coordinates start, Coordinates end) {
        return (float) Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }
}
