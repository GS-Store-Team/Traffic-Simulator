package com.traffic_simulator.businnes_logic.models.buildings;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Building extends GraphObject {
    private final String tmp = "Building";

    private Coordinates upLeftCorner;
    private Coordinates downRightCorner;

    private String name;
    private String street;
    private String index;

    private ParkingZone parkingZone;

    public Building(Coordinates upLeftCorner, Coordinates downRightCorner, String name, String street, String index) {
        super();
        this.upLeftCorner = upLeftCorner;
        this.downRightCorner = downRightCorner;
        this.name = name;
        this.street = street;
        this.index = index;
        this.parkingZone = new ParkingZone(0, upLeftCorner, downRightCorner);
    }

    public Building(Coordinates upLeftCorner, Coordinates downRightCorner, String name, String street, String index, ParkingZone parkingZone) {
        super();
        this.upLeftCorner = upLeftCorner;
        this.downRightCorner = downRightCorner;
        this.name = name;
        this.street = street;
        this.index = index;
        this.parkingZone = parkingZone;
    }
}
