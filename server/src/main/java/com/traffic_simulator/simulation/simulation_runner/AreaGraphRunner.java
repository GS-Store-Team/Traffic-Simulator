
package com.traffic_simulator.simulation.simulation_runner;

import com.traffic_simulator.dto.CarDTO;
import com.traffic_simulator.dto.SimulationStateDTO;
import com.traffic_simulator.simulation.AreaGraph;
import com.traffic_simulator.simulation.models.Car;
import com.traffic_simulator.utils.Converters;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class AreaGraphRunner {
    private final AreaGraph graph;
    private List<Car> cars;

    public AreaGraphRunner(AreaGraph graph) {
        this.graph = graph;
    }

    public void update() {
        graph.getRoads().forEach(sr -> {
            sr.update();
            if (sr.canPushForward()) {
                sr.pushForward(new Car());
            }
        });
    }

    public SimulationStateDTO getState() {
        AtomicLong id = new AtomicLong(0);
        return new SimulationStateDTO(
                List.of(Converters.toAreaVersionDTO(graph.getVersion())),
                new HashSet<>(graph.getRoads()
                        .stream()
                        .map(sr -> List.of(sr.getForward(), sr.getReverse()))
                        .flatMap(lists -> lists.stream())
                        .flatMap(lanes -> lanes.stream())
                        .map(lane -> lane.getCells())
                        .flatMap(cells -> cells.stream())
                        .map(c -> new CarDTO(id.getAndIncrement(), c.getCoordinates()))
                        .toList())
        );
    }
}

