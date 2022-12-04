package com.traffic_simulator.businnes_logic.models.buildings;

import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PedestrianArea extends Building {
    public PedestrianArea(Coordinates upLeftCorner, Coordinates downRightCorner, String name, String street, String index) {
        super(upLeftCorner, downRightCorner, name, street, index);
    }

    public PedestrianArea(Coordinates upLeftCorner, Coordinates downRightCorner, String name, String street, String index, ParkingZone parkingZone) {
        super(upLeftCorner, downRightCorner, name, street, index, parkingZone);
    }
}
