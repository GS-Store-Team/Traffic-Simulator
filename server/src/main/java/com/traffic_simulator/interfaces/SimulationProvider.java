package com.traffic_simulator.interfaces;

import com.traffic_simulator.dto.SimulationStateDTO;

import java.util.Map;

public interface SimulationProvider {
    void run(Map<Long, Long> areaIdVersionId);
    void stop();
    void play();
    void destroy();
    SimulationStateDTO state();
}
