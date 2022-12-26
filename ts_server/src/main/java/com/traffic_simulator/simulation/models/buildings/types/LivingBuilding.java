package com.traffic_simulator.simulation.models.buildings.types;

import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.buildings.ParkingZone;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LivingBuilding extends Building {
    public LivingBuilding(Coordinates upLeftCorner, String name, String street, String index, BuildingType type) {
        super(upLeftCorner,  name, street, index, new ParkingZone(10, new Coordinates(10,10)));
    }

    public LivingBuilding(Coordinates upLeftCorner, String name, String street, String index, ParkingZone parkingZone) {
        super(upLeftCorner,  name, street, index, parkingZone);
    }
}