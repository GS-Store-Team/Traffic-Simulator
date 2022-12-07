package com.traffic_simulator.businnes_logic.models.road;

import com.traffic_simulator.businnes_logic.models.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
                    startPoint.getCoordinates(),
                    endPoint.getCoordinates()));
        }

        for (int i = 0; i < leftLanesAmount; i++) {
            rightLanes.add(new Lane(
                    startPoint.getCoordinates(),
                    endPoint.getCoordinates()));
        }
    }

    private double computeNaturalWeightByCoordinates() {
        return rightLanes.get(0).getCells().size() * GlobalSettings.cellWeightModifier;
    }

    private double computeTrafficWeight(List<Lane> lanes) {
        double weight = 0;
        for (Lane lane : lanes) {
            weight += lane.computeTrafficWeight();
        }

        return weight;
    }

    public static Coordinates computeRoadVectorCoordinates(Road road) {
        return new Coordinates(
                road.endPoint.getCoordinates().x - road.startPoint.getCoordinates().x,
                road.endPoint.getCoordinates().y - road.startPoint.getCoordinates().y);
    }

    public static double computeRoadVectorLength(Road road) {
        return (int) Math.sqrt(
                Math.pow(computeRoadVectorCoordinates(road).x, 2) +
                        Math.pow(computeRoadVectorCoordinates(road).y, 2)
        );
    }

    public static double computeScalarProduct(Coordinates vector1, Coordinates vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y;
    }

    public static double computeRoadsAngleCos(Road road1, Road road2) {
        return computeScalarProduct(computeRoadVectorCoordinates(road1), computeRoadVectorCoordinates(road2)) /
                (computeRoadVectorLength(road1) * computeRoadVectorLength(road2));
    }
}
