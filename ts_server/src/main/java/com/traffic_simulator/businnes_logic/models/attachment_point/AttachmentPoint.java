package com.traffic_simulator.businnes_logic.models.attachment_point;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter @ToString
public class AttachmentPoint extends GraphObject {
    private Coordinates coordinates;
    public AttachmentPoint(Coordinates coordinates) {
        super();
        this.coordinates = coordinates;
    }

    public Coordinates computeRoadVectorCoordinates(Road road) {
        return new Coordinates(
                road.getEndPoint().getCoordinates().x - coordinates.x,
                road.getEndPoint().getCoordinates().y - coordinates.y);
    }

    public double computeRoadVectorLength(Road road) {
        return (int) Math.sqrt(
                Math.pow(computeRoadVectorCoordinates(road).x, 2) +
                        Math.pow(computeRoadVectorCoordinates(road).y, 2)
        );
    }

    public double computeScalarProduct(Coordinates vector1, Coordinates vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y;
    }

    public double computeRoadsAngleCos(Road road1, Road road2) {
        return computeScalarProduct(computeRoadVectorCoordinates(road1), computeRoadVectorCoordinates(road2)) /
                (computeRoadVectorLength(road1) * computeRoadVectorLength(road2));
    }


    /**
     * Calculate traffic weight.
     *
     * @return traffic weight distinguished to lane packs (if it is road).
     */
    @Override
    public Map<Integer, Double> getTrafficWeight() {
        HashMap<Integer, Double> hashMap = new HashMap<>();
        hashMap.put(0, 1 * GlobalSettings.cellTrafficWeightModifier);
        return hashMap;
    }
}
