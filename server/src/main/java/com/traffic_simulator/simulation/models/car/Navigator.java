
package com.traffic_simulator.simulation.models.car;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.RoadSide;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import com.traffic_simulator.simulation.models.supportive.cell.Cell;
import com.traffic_simulator.simulation.models.supportive.cell.CrossroadCell;
import com.traffic_simulator.simulation.simulation_runner.algorithms.SimulationSettings;
import com.traffic_simulator.simulation.simulation_runner.algorithms.pathfinding.car_path.CarPath;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Setter
@Getter
@ToString
public class Navigator implements Serializable {
    private double acceleration;
    private double velocity;
    private double accelerationUpperLimit;
    private double accelerationLowerLimit;
    private long departureTime;
    private long workTime;
    private double weight = 1;

    private List<Cell> advance;
    private Node currentNode;
    private Edge currentEdge;
    private List<Edge> currentEdgeBunch;
    private final CarPath carPath;

    private Coordinates currentCoordinate;
    private double currentCos;
    private double currentSin;

    private int index;
    private boolean carRunning;
    private boolean temp = false;
    private Car car;

    private RoadSide roadSide;

    public enum MoveState implements Serializable {NODE, ROAD, NONE}

    private MoveState moveState;

    private CrossroadCell crossroadCellStart;
    private CrossroadCell crossroadCellEnd;
    private int xCellsRemaining = 0;
    private int yCellsRemaining = 0;
    private CrossroadCell currentCrossroadCell;
    private CrossroadCell previousCrossroadCell;
    private int i = 0;
    private boolean hacCounted = false;

    private enum NodeMoveDirection implements Serializable {
        HORIZONTAL,
        VERTICAL
    }

    private NodeMoveDirection nodeMoveDirection;

    public Navigator(Car car, int accelerationLowerLimit, int accelerationUpperLimit, CarPath carPath) {

        this.car = car;
        this.carPath = SerializationUtils.clone(carPath);
        this.advance = new ArrayList<>();
        this.moveState = MoveState.NONE;

        this.departureTime = 0L;
        this.workTime = 0L;

        this.accelerationUpperLimit = accelerationUpperLimit;
        this.accelerationLowerLimit = accelerationLowerLimit;
        initSpeedometers();

        initSelfPositioning();

        this.carRunning = false;
    }

    private void initTimers() {
        this.departureTime = 0L;
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
            car.setCurrentPosition(currentCoordinate);
            System.out.println("Car#" + car.getId() + " appeared: " + this.currentNode.getNodeIndex() + " car coordinate: "
                    + car.getCurrentPosition());
        } catch (NoSuchElementException e) {
            System.out.println("No nodes in Car#" + car.getId() + " path! Init place: " + carPath.getStart().getNodeIndex());
        }

    }

    public void update(long currentTick) {
        System.out.println("In update");
        System.out.println("time: " + departureTime + " " + carRunning + " tick: " + currentTick);
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

    private void updateCar() {

       /* Edge oldEdge = currentEdge;
        Node oldNode = currentNode;*/

        switch (moveState) {
            case NODE -> {
                //decideInNode();
                //moveCarInNode();
                //if (currentCoordinate == crossroadCellEnd.getCoordinates()) {
                currentEdgeBunch = carPath.getEdges().pop();
                //currentEdgeBunch = carPath.getEdges().pollFirst();

                currentEdge = currentEdgeBunch.get(0);

                currentNode.getNavigators().remove(this);
                currentEdge.getNavigators().add(this);

                crossroadCellStart = null;
                crossroadCellEnd = null;

                moveState = MoveState.ROAD;
                car.setCurrentPosition(currentCoordinate);
                //}
            }
            case ROAD -> {
                System.out.println("currentX " + currentCoordinate.getX());
                System.out.println("currentY " + currentCoordinate.getY());
                System.out.println("finalX" + currentEdge.getEnd().getAttachmentPoint().getCoordinates().getX());
                System.out.println("finalY" + currentEdge.getEnd().getAttachmentPoint().getCoordinates().getY());
                if (currentCoordinate.getX() == currentEdge.getEnd().getAttachmentPoint().getCoordinates().getX() &&
                currentCoordinate.getY() == currentEdge.getEnd().getAttachmentPoint().getCoordinates().getY()) {
                    System.out.println("in if");
                    currentNode = carPath.getNodes().pollFirst();
                    moveState = MoveState.NODE;
                    currentEdge.getNavigators().remove(this);
                    currentNode.getNavigators().add(this);
                }
                else if (currentCoordinate == currentEdge.getStart().getAttachmentPoint().getCoordinates()) {
                    decideInRoad();
                    moveInRoad(currentEdge.getStart().getAttachmentPoint().getCoordinates(),
                            currentEdge.getEnd().getAttachmentPoint().getCoordinates());
                }
            }
            case NONE -> {
            }

        }

    }

    public double getInEdgeRelativePosition() {
        if (moveState == MoveState.ROAD) {
            double fullNumber = currentEdgeLength();
            double currentNumber = Math.sqrt(
                    Math.pow(currentCoordinate.getX(), 2) + Math.pow(currentCoordinate.getY(), 2)
                            - Math.pow(currentEdge.getStart().getAttachmentPoint().getCoordinates().getX(), 2) + Math.pow(currentEdge.getStart().getAttachmentPoint().getCoordinates().getY(), 2));
            return currentNumber / fullNumber;
        } else return 0;
    }

    private void decideInRoad() {
        try {
            Edge fasterEdge = currentEdgeBunch.stream().min(Comparator.comparingDouble(Edge::getWeight)).orElseThrow();
            boolean canChangeLane = currentEdge.getNavigators().stream().anyMatch(
                    navigator -> (navigator.currentEdge.equals(fasterEdge) &
                            Math.abs(navigator.getCurrentCoordinate().getX() - this.currentCoordinate.getX()) < GlobalSettings.automobileLength * 4 ||
                            Math.abs(navigator.getCurrentCoordinate().getY() - this.currentCoordinate.getY()) < GlobalSettings.automobileLength * 4 &
                                    navigator.getInEdgeRelativePosition() < this.getInEdgeRelativePosition()
                    )
            );

            if (canChangeLane) {
                car.setCurrentVelocity(car.getCurrentVelocity() * 0.5f);
                currentEdge.getNavigators().remove(this);
                currentEdge = fasterEdge;
                fasterEdge.getNavigators().add(this);
                return;
            }
        } catch (NoSuchElementException ignored) {

        }

        try {
            Navigator forwardNavigator = currentEdge.getNavigators().stream().filter(
                    navigator -> (
                            Math.abs(navigator.getCurrentCoordinate().getX() - this.currentCoordinate.getX()) <= GlobalSettings.automobileLength * 5 ||
                                    Math.abs(navigator.getCurrentCoordinate().getY() - this.currentCoordinate.getY()) <= GlobalSettings.automobileLength * 5 &
                                            navigator.getInEdgeRelativePosition() > this.getInEdgeRelativePosition()
                    )
            ).findFirst().orElseThrow();

            double xSub = forwardNavigator.getCurrentCoordinate().getX() - this.currentCoordinate.getX();
            double ySub = forwardNavigator.getCurrentCoordinate().getY() - this.currentCoordinate.getY();

            if (xSub > GlobalSettings.automobileLength * 2 ||
                    ySub > GlobalSettings.automobileLength * 2) {
                car.setCurrentVelocity(car.getCurrentVelocity() * 0.5f);
            } else if (xSub <= GlobalSettings.automobileLength ||
                    ySub <= GlobalSettings.automobileLength) {
                car.setCurrentVelocity(0);
            } else {
                if (car.getCurrentVelocity() <= 0) {
                    car.setCurrentVelocity(3);
                } else {
                    car.setCurrentVelocity((car.getCurrentVelocity() * 1.5f) % SimulationSettings.automobileMaxVelocity);
                }
            }

        } catch (NoSuchElementException ignored) {

        }
    }

    private void moveInRoad(Coordinates roadStartPoint, Coordinates roadEndPoint) {


        System.out.println("here");
        System.out.println("rs" + roadStartPoint.getX() + " " + roadStartPoint.getY());
        car.setCurrentVelocity(50);
        if (!this.hacCounted) {
            double length = currentEdgeLength();
            currentCos = (roadEndPoint.getX() - roadStartPoint.getX()) / length;
            currentSin = (roadEndPoint.getY() - roadStartPoint.getY()) / length;
            this.hacCounted = true;
            currentCoordinate.setY(currentEdge.getRefRoad().getLeftLanes().get(0).getStartCoordinates().getY()+5*currentSin);
            currentCoordinate.setX(currentEdge.getRefRoad().getLeftLanes().get(0).getStartCoordinates().getX());
        }else{
            currentCoordinate.setX(currentCoordinate.getX() + car.getCurrentVelocity() * currentCos);
            currentCoordinate.setY(currentCoordinate.getY() + car.getCurrentVelocity() * currentSin);
        }
        System.out.println("cos and sin: " + currentCos + " " + currentSin);

        System.out.println("new coordinate: " + currentCoordinate.getX() + " " + currentCoordinate.getY());
        car.setCurrentPosition(currentCoordinate);
        if (Math.sqrt(Math.pow(roadEndPoint.getX() - currentCoordinate.getX(),2) +
                Math.pow(roadEndPoint.getY() - currentCoordinate.getY(),2)) < car.getCurrentVelocity() ){
            currentCoordinate.setY(roadEndPoint.getY());
            currentCoordinate.setX(roadEndPoint.getX());
        }
    }

    private void decideInNode() {
        currentEdgeBunch = carPath.getEdges().pop();
        currentEdge = currentEdgeBunch.get(0);
        if (currentCrossroadCell == crossroadCellEnd) {
            crossroadCellStart = currentEdge.getRefLane().getEndingCrossroadCell();
            currentCrossroadCell = crossroadCellStart;
            previousCrossroadCell = crossroadCellStart;
            return;
        }
        crossroadCellStart = currentEdge.getRefLane().getEndingCrossroadCell();
        currentCrossroadCell = crossroadCellStart;
        previousCrossroadCell = crossroadCellStart;
        if (crossroadCellStart == null || crossroadCellEnd == null) {
            crossroadCellStart = currentEdge.getRefLane().getEndingCrossroadCell();
            currentCrossroadCell = crossroadCellStart;
            previousCrossroadCell = crossroadCellStart;
            crossroadCellEnd = carPath.getEdges().peek()
                    .get(
                            currentEdge.getRefLane().getLocalId() % carPath.getEdges().peek().size())
                    .getRefLane().getStartingCrossroadCell();
            xCellsRemaining = currentNode.getAttachmentPoint().getXSide();
            yCellsRemaining = currentNode.getAttachmentPoint().getYSide();

            if (xCellsRemaining > yCellsRemaining) {
                nodeMoveDirection = NodeMoveDirection.HORIZONTAL;
            } else {
                nodeMoveDirection = NodeMoveDirection.VERTICAL;
            }
        }

        CrossroadCell[][] cellMatrix = currentNode.getAttachmentPoint().getCrossroadCellMatrix();

        switch (nodeMoveDirection) {
            case HORIZONTAL -> {
                if (currentCrossroadCell.getX() != crossroadCellEnd.getX()) {
                    if (currentCrossroadCell.getX() > crossroadCellEnd.getX()) {
                        if (!currentCrossroadCell.isOccupied()) {
                            previousCrossroadCell = currentCrossroadCell;
                            currentCrossroadCell = currentCrossroadCell.getLeftCell();
                        }
                    } else if (currentCrossroadCell.getX() < crossroadCellEnd.getX()) {
                        if (!currentCrossroadCell.isOccupied()) {
                            previousCrossroadCell = currentCrossroadCell;
                            currentCrossroadCell = currentCrossroadCell.getRightCell();
                        }
                    } else {
                        nodeMoveDirection = NodeMoveDirection.VERTICAL;
                    }
                }
            }
            case VERTICAL -> {
                if (currentCrossroadCell.getY() > crossroadCellEnd.getY()) {
                    if (!currentCrossroadCell.isOccupied()) {
                        previousCrossroadCell = currentCrossroadCell;
                        currentCrossroadCell = currentCrossroadCell.getDownCell();
                    }
                } else if (currentCrossroadCell.getY() < crossroadCellEnd.getY()) {
                    if (!currentCrossroadCell.isOccupied()) {
                        previousCrossroadCell = currentCrossroadCell;
                        currentCrossroadCell = currentCrossroadCell.getUpCell();
                    }
                } else {
                    nodeMoveDirection = NodeMoveDirection.HORIZONTAL;
                }
            }
        }
    }

    private void moveCarInNode() {







        crossroadCellStart = currentEdge.getRefLane().getEndingCrossroadCell();
        System.out.println("current edge:" + currentEdge.getRefLane().getEndingCrossroadCell());
        System.out.println("starter: "+ crossroadCellStart);
        currentCrossroadCell = crossroadCellStart;
        previousCrossroadCell = currentCrossroadCell;
        previousCrossroadCell.setOccupied(false);
        currentCrossroadCell.setOccupied(true);
        currentCoordinate.setX(currentCrossroadCell.getX());
        currentCoordinate.setY(currentCrossroadCell.getY());
    }

    private double currentEdgeLength() {
        Coordinates start = currentEdge.getStart().getAttachmentPoint().getCoordinates();
        Coordinates end = currentEdge.getEnd().getAttachmentPoint().getCoordinates();

        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }
}

