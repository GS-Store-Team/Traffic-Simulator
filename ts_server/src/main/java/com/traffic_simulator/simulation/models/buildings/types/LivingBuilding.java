package com.traffic_simulator.simulation.models.buildings.types;

import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.buildings.ParkingZone;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LivingBuilding extends Building {
    public LivingBuilding(Coordinates upLeftCorner, Coordinates downRightCorner, Coordinates center, String name, String street, String index) {
        super(upLeftCorner, downRightCorner, center, name, street, index);
    }

    public LivingBuilding(Coordinates upLeftCorner, Coordinates downRightCorner, Coordinates center, String name, String street, String index, ParkingZone parkingZone) {
        super(upLeftCorner, downRightCorner, center, name, street, index, parkingZone);
    }
}
