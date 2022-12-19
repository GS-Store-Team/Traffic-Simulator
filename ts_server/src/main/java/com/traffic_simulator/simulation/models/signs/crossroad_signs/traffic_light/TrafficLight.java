package com.traffic_simulator.simulation.models.signs.crossroad_signs.traffic_light;

import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.signs.crossroad_signs.CrossroadSign;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrafficLight extends CrossroadSign {
    private Road inRoad;
    private Road outRoad;
    private TrafficLightState currentState;

    private int redTime;
    private int yellowTime;
    private int greenTime;

    private TrafficLight nextTrafficLight;

    public TrafficLight(Road inRoad, Road outRoad, int redTime, int yellowTime, int greenTime, TrafficLight nextTrafficLight) {
        this.inRoad = inRoad;
        this.outRoad = outRoad;
        this.currentState = TrafficLightState.NONE;
        this.redTime = redTime;
        this.yellowTime = yellowTime;
        this.greenTime = greenTime;
        this.nextTrafficLight = nextTrafficLight;
    }
}
