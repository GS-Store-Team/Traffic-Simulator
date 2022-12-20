package com.traffic_simulator.simulation.models.buildings;

import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.supportive.BuildingType;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class Building extends MapObject {
    BuildingType type;
    private Coordinates upLeftCorner;
    private Coordinates center;
    private int width = 50;
    private AttachmentPoint connectedPoint = null;

    private String name;
    private String street;
    private String index;
    private ParkingZone parkingZone;

    //TODO Сделать более легкий конструктор для Building и потомков
    public Building(Coordinates upLeftCorner,  String name, String street, String index, BuildingType type) {
        super();
        this.upLeftCorner = upLeftCorner;
        this.name = name;
        this.center.setX(this.upLeftCorner.getX() + this.width/2);
        this.center.setY(this.upLeftCorner.getY() + this.width/2);
        this.street = street;
        this.index = index;
        this.type = type;
        this.parkingZone = new ParkingZone(0, upLeftCorner);
    }

    public Building(Coordinates upLeftCorner,   String name, String street, String index, ParkingZone parkingZone) {
        super();
        this.upLeftCorner = upLeftCorner;
        this.name = name;
        this.street = street;
        this.index = index;
        this.parkingZone = parkingZone;
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
