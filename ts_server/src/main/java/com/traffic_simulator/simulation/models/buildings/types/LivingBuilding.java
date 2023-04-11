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

    private int residingCars;

    public LivingBuilding(long id, Coordinates upLeftCorner, BuildingType buildingType) {
        super(id, upLeftCorner,  buildingType);
    }
    public LivingBuilding(long id, Coordinates upLeftCorner, String name, String street, String index, BuildingType type, int residingCars) {
        super(id, upLeftCorner,  name, street, index, new ParkingZone(10, new Coordinates(10,10)));
        this.residingCars = residingCars;
    }

    public LivingBuilding(long id, Coordinates upLeftCorner, String name, String street, String index, ParkingZone parkingZone, int residingCars) {
        super(id, upLeftCorner,  name, street, index, parkingZone);
        this.residingCars = residingCars;
    }
}
