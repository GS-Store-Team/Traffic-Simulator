package com.traffic_simulator.businnes_logic.simulation_runner;

import com.traffic_simulator.businnes_logic.RoadMap;
import com.traffic_simulator.dto.SimulationDTO;

public class SimulationRunner {
     private final RoadMap roadMap;

    public SimulationRunner(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public void start(){
        //heavy initialization
        play();
    }
    public void play(){}
    public void stop(){}

    public SimulationDTO getCurrentSimulationState() {
        return new SimulationDTO();
    }
}
