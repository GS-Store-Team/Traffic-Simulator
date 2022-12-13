package com.traffic_simulator.businnes_logic.models.buildings.types;

import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.buildings.ParkingZone;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EntertainmentBuilding extends Building {
    public EntertainmentBuilding(Coordinates upLeftCorner, Coordinates downRightCorner, Coordinates center, String name, String street, String index) {
        super(upLeftCorner, downRightCorner, center, name, street, index);
    }

    public EntertainmentBuilding(Coordinates upLeftCorner, Coordinates downRightCorner, Coordinates center, String name, String street, String index, ParkingZone parkingZone) {
        super(upLeftCorner, downRightCorner, center, name, street, index, parkingZone);
    }
}
