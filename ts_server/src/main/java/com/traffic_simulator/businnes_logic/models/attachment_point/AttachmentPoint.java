package com.traffic_simulator.businnes_logic.models.attachment_point;

import com.traffic_simulator.businnes_logic.GlobalSettings;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class AttachmentPoint extends GraphObject {
    protected Coordinates coordinates;
    protected List<Building> connectedBuildings;
    public AttachmentPoint(Coordinates coordinates) {
        super();
        this.coordinates = coordinates;
    }

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
