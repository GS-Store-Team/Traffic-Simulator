package com.traffic_simulator.businnes_logic.models.road;

import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CurveRoad extends Road {
    private Coordinates roundingPoint1;
    private Coordinates roundingPoint2;

    public CurveRoad(Coordinates startCoordinates, Coordinates endCoordinates, int rightLanesAmount, int leftLanesAmount, Coordinates roundingPoint1, Coordinates roundingPoint2) {
        super(startCoordinates, endCoordinates, rightLanesAmount, leftLanesAmount);
        this.roundingPoint1 = roundingPoint1;
        this.roundingPoint2 = roundingPoint2;
    }
}
