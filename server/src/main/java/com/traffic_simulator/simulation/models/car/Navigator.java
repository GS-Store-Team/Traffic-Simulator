
package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.RoadSide;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.car_path.CarPath;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
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
    private Node currentNode;
    private Edge currentEdge;
    private final CarPath carPath;

    private Coordinates currentCoordinate;
    private double currentCos;
    private double currentSin;

    private int index;
    private boolean carRunning;
    private boolean temp = false;
    private Car car;

    private RoadSide roadSide;

    public enum MoveState {NODE, ROAD, NONE}

    private MoveState moveState;

    public Navigator(Car car, int accelerationLowerLimit, int accelerationUpperLimit, CarPath carPath) {

        this.car = car;
        this.carPath = carPath;
        this.advance = new ArrayList<>();
        this.moveState = MoveState.NONE;

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
            this.moveState = MoveState.NODE;
            this.currentEdge = null;
            currentCoordinate = currentNode.getAttachmentPoint().getCoordinates();
            System.out.println("Car#" + car.getId() + " appeared: " + this.currentNode.getNodeIndex());
        } catch (NoSuchElementException e) {
            System.out.println("No nodes in Car#" + car.getId() + " path! Init place: " + carPath.getStart().getNodeIndex());
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

        switch (moveState) {
            case NODE -> {
                moveCarInNode();
                if (currentCoordinate == carPath.getEdges().peek().getStart().getAttachmentPoint().getCoordinates()) {
                    currentEdge = carPath.getEdges().pop();
                    moveState = MoveState.ROAD;
                }
            }
            case ROAD -> {
                if (currentCoordinate == currentEdge.getStart().getAttachmentPoint().getCoordinates()) {
                    moveCarInRoad(
                            currentEdge.getStart().getAttachmentPoint().getCoordinates(),
                            currentEdge.getEnd().getAttachmentPoint().getCoordinates());
                } else if (currentCoordinate == currentEdge.getEnd().getAttachmentPoint().getCoordinates()) {
                    currentNode = carPath.getNodes().pop();
                    moveState = MoveState.NODE;
                }
            }
            case NONE -> {
            }
        }

    }

    private void changeLane() {
        switch (moveState) {
            case NODE -> {
                currentEdge = carPath.getEdges()
                        .stream()
                        .toList()
                        .stream()
                        .min(
                                Comparator.comparingDouble(Edge::getWeight))
                        .get();
                currentCoordinate = currentEdge.getStart().getAttachmentPoint().getCoordinates();
            }
            case ROAD -> {

            }
            case NONE -> {
            }
        }
    }

    private void moveCarInRoad(Coordinates roadStartPoint, Coordinates roadEndPoint) {
        double length = lengthRoad(roadStartPoint, roadEndPoint);
        currentCos = (roadEndPoint.getX() - roadStartPoint.getX()) / length;
        currentSin = (roadEndPoint.getY() - roadStartPoint.getY()) / length;
        currentCoordinate.setX(currentCoordinate.getX() + car.getCurrentVelocity() * currentCos);
        currentCoordinate.setY(currentCoordinate.getY() + car.getCurrentVelocity() * currentSin);
    }

    private void moveCarInNode() {

    }

    private double lengthRoad(Coordinates start, Coordinates end) {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }
}

