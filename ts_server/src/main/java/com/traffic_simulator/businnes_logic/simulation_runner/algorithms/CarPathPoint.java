package com.traffic_simulator.businnes_logic.simulation_runner.algorithms;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.road.Road;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class CarPathPoint {
    private GraphObject graphObject;
    private CarPathPoint prevPoint;
    private Road roadToPrev;

    private double trafficWeight;
    private double naturalWeight;

    public CarPathPoint(GraphObject graphObject) {
        this.graphObject = graphObject;
        this.prevPoint = null;
        this.roadToPrev = null;
    }

    private void calculatePathNaturalWeight() {
        double weight = roadToPrev.getNaturalWeight();
        CarPathPoint cyclicPrevPoint = this.prevPoint;
        while (cyclicPrevPoint != null) {
            weight += cyclicPrevPoint.getGraphObject().getNaturalWeight();
            cyclicPrevPoint = cyclicPrevPoint.getPrevPoint();
        }
        naturalWeight = weight;
    }

    //TODO Найти способ считать без рекурсии

    protected double calculatePathTrafficWeight() {
        double weight = 0;

        if (roadToPrev.getStartPoint() == graphObject) {
            weight += roadToPrev.computeRightTrafficWeight();
        } else {
            weight += roadToPrev.computeLeftTrafficWeight();
        }

        CarPathPoint recursivePrevPoint = this.prevPoint;
        if (recursivePrevPoint != null) {
            weight += recursivePrevPoint.calculatePathTrafficWeight();
        }
        return weight;
    }

    //TODO Дописать
    public void setPrevPoint(CarPathPoint prevPoint) {
        this.prevPoint = prevPoint;
        //this.roadToPrev = graphObject.
        calculatePathNaturalWeight();
        trafficWeight = calculatePathTrafficWeight();
    }
}
