package com.traffic_simulator.simulation.models.attachment_point;

import com.traffic_simulator.simulation.GlobalSettings;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.road.Lane;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.Coordinates;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;


@Data
public class AttachmentPoint extends MapObject {
    private final Coordinates coordinates;
    private final List<Building> connectedBuildings;
    private final List<Road> startingRoads;
    private final List<Road> finishingRoads;

    public AttachmentPoint(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.connectedBuildings = new ArrayList<>();
        this.startingRoads = new ArrayList<>();
        this.finishingRoads = new ArrayList<>();
    }
    @Override
    public Map<Integer, Double> getTrafficWeight() {
        HashMap<Integer, Double> hashMap = new HashMap<>();
        hashMap.put(0, 1 * GlobalSettings.cellTrafficWeightModifier);
        return hashMap;
    }
    public void addBuilding(Building building) {
        connectedBuildings.add(building);
    }

    public void addStartingRoad(Road road){
        startingRoads.add(road);
    }
    public void addFinishingRoad(Road road){
        finishingRoads.add(road);
    }
}
