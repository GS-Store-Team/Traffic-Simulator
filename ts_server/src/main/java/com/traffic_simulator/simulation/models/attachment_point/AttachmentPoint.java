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
    protected Coordinates coordinates;
    protected List<Building> connectedBuildings;
    protected List<Road> roads;
    protected List<Road> endingRoads;
    protected List<Road> startingRoads;

    public AttachmentPoint(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.connectedBuildings = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.endingRoads = new ArrayList<>();
        this.startingRoads = new ArrayList<>();

/*        setEntryLanes();
        setOutputLanes();*/
    }

    protected void dispenseRoadsByEnds() {
        for (Road road : roads) {
            if (road.getStartPoint().equals(this)) {
                startingRoads.add(road);
            } else if (road.getEndPoint().equals(this)) {
                endingRoads.add(road);
            }
        }
    }

/*    protected void setEntryLanes() {
        for (Road road : roads) {
            entryLanes.put(road, road.getRightLanes());
        }
    }

    protected void setOutputLanes() {
        for (Road road : roads) {
            entryLanes.put(road, road.getLeftLanes());
        }
    }*/
    /**
     * Calculate traffic weight.
     *
     * @return traffic weight distinguished to lane packs (if it is road).
     */
    @Override
    public Map<Integer, Double> getTrafficWeight() {
        HashMap<Integer, Double> hashMap = new HashMap<>();
        hashMap.put(0, 1 * GlobalSettings.cellTrafficWeightModifier);
        return hashMap;
    }

    public void addBuilding(Building building) {
        connectedBuildings.add(building);
    }

    public void removeBuilding(Building building) {
        connectedBuildings.remove(building);
    }
}
