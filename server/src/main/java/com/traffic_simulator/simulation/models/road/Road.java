package com.traffic_simulator.simulation.models.road;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
//@ToString
public class Road extends MapObject {
    private Coordinates startCoordinate;
    private Coordinates endCoordinate;
    private List<Lane> rightLanes;
    private List<Lane> leftLanes;
    private final double weight = 1;

    public Road(Coordinates startPoint, Coordinates endPoint, int rightLanesAmount, int leftLanesAmount) {
        this.startCoordinate = startPoint;
        this.endCoordinate = endPoint;
        this.rightLanes = new ArrayList<>();
        this.leftLanes = new ArrayList<>();
        //this.naturalWeight = computeNaturalWeightByCoordinates();
        addLanes(rightLanesAmount, leftLanesAmount);
    }

    private void addLanes(int rightLanesAmount, int leftLanesAmount) {
        for (int i = 0; i < rightLanesAmount; i++)
            rightLanes.add(new Lane(i, startCoordinate, endCoordinate));

        for (int i = 0; i < leftLanesAmount; i++)
            leftLanes.add(new Lane(i, endCoordinate, startCoordinate));
    }

    public double computeNaturalWeightByCoordinates() {
        return rightLanes.get(0).getCells().size() * GlobalSettings.cellNaturalWeightModifier;
    }

    private double computeLanePackTrafficWeight(List<Lane> lanes) {
        double weight = 0;
        for (Lane lane : lanes) {
            weight += lane.computeTrafficWeight();
        }

        return weight;
    }

    //
    public double computeRightTrafficWeight() {
        return computeLanePackTrafficWeight(rightLanes);
    }

    //
    public double computeLeftTrafficWeight() {
        return computeLanePackTrafficWeight(leftLanes);
    }

    /**
     * Calculate traffic weight.
     *
     * @return traffic weight distinguished to lane packs (if it is road).
     */
    @Override
    public Map<Integer, Double> getTrafficWeight() {
        HashMap<Integer, Double> hashMap = new HashMap<>();
        hashMap.put(-1, computeLeftTrafficWeight());
        hashMap.put(1, computeRightTrafficWeight());
        return hashMap;
    }
}
