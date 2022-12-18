package com.traffic_simulator.dto;

import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.car.Car;
import com.traffic_simulator.businnes_logic.models.signs.crossroad_signs.traffic_light.TrafficLightState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class SimulationDTO {
    private List<Car> cars;
    private List<AttachmentPoint> attachmentPoints;
    private List<Building> buildings;
    private List<TrafficLightState> trafficLightStates;
}