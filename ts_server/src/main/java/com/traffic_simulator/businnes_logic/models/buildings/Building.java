package com.traffic_simulator.businnes_logic.models.buildings;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class Building extends GraphObject {
    private Coordinates upLeftCorner;
    private Coordinates downRightCorner;
    private Coordinates center;
    private AttachmentPoint connectedPoint = null;

    private String name;
    private String street;
    private String index;

    private ParkingZone parkingZone;

    //TODO Сделать более легкий конструктор для Building и потомков
    public Building(Coordinates upLeftCorner, Coordinates downRightCorner, Coordinates center, String name, String street, String index) {
        super();
        this.upLeftCorner = upLeftCorner;
        this.downRightCorner = downRightCorner;
        this.name = name;
        this.street = street;
        this.index = index;
        this.parkingZone = new ParkingZone(0, upLeftCorner, downRightCorner);
        this.center = center;
    }

    public Building(Coordinates upLeftCorner, Coordinates downRightCorner, Coordinates center, String name, String street, String index, ParkingZone parkingZone) {
        super();
        this.upLeftCorner = upLeftCorner;
        this.downRightCorner = downRightCorner;
        this.name = name;
        this.street = street;
        this.index = index;
        this.parkingZone = parkingZone;
        this.center = center;
    }

    /**
     * Calculate traffic weight.
     *
     * @return traffic weight distinguished to lane packs (if it is road).
     */
    @Override
    public Map<Integer, Double> getTrafficWeight() {
        HashMap<Integer, Double> hm = new HashMap<>();
        hm.put(0, 0.0);
        return hm;
    }
}
