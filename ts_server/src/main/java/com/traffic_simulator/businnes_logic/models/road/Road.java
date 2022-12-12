package com.traffic_simulator.businnes_logic.models.road;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Road extends GraphObject {
    private AttachmentPoint startPoint;
    private AttachmentPoint endPoint;
    private List<Lane> rightLanes;
    private List<Lane> leftLanes;

    public Road(Coordinates startCoordinates, Coordinates endCoordinates, int rightLanesAmount, int leftLanesAmount) {
        super();

        this.startPoint = new AttachmentPoint(startCoordinates);
        this.endPoint = new AttachmentPoint(endCoordinates);
        this.rightLanes = new ArrayList<>();
        this.leftLanes = new ArrayList<>();
        addLanes(rightLanesAmount, leftLanesAmount);

        this.naturalWeight = computeNaturalWeightByCoordinates();
    }

    public void addLanes(int rightLanesAmount, int leftLanesAmount) {
        for (int i = 0; i < rightLanesAmount; i++) {
            rightLanes.add(new Lane(
                    i + 1,
                    startPoint.getCoordinates(),
                    endPoint.getCoordinates()));
        }

        for (int i = 0; i < leftLanesAmount; i++) {
            rightLanes.add(new Lane(
                    -(i + 1),
                    startPoint.getCoordinates(),
                    endPoint.getCoordinates()));
        }
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

    public double computeRightTrafficWeight() {
        return computeLanePackTrafficWeight(rightLanes);
    }

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
        //To-Do make logic
        return null;
    }
}
