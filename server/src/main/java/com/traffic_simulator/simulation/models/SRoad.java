package com.traffic_simulator.simulation.models;

import com.traffic_simulator.models.Road;
import com.traffic_simulator.utils.Converters;
import com.traffic_simulator.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
@Setter
public class SRoad {
    private final long id = 0;
    private Coordinate start;
    private Coordinate end;
    private List<Lane> forward;
    private List<Lane> reverse;

    public SRoad(Road road) {
        this.start = Converters.pointToCoordinates(road.getStart());
        this.end = Converters.pointToCoordinates(road.getEnd());
        this.forward = IntStream
                .range(0, Math.toIntExact(road.getForward()))
                .mapToObj(i -> {
                    var pair = Utils.computePoints(start, end, true, i);
                    return new Lane(pair[0], pair[1]);
                }).toList();
        this.reverse = IntStream
                .range(0, Math.toIntExact(road.getReverse()))
                .mapToObj(i -> {
                    var pair = Utils.computePoints(start, end, false, i);
                    return new Lane(pair[0], pair[1]);
                }).toList();
    }

    public boolean canPushForward() {
        return forward.stream().filter(Lane::canPush).toList().size() > 0;
    }

    public boolean canPushReverse() {
        return reverse.stream().filter(Lane::canPush).toList().size() > 0;
    }

    public void pushForward(Car car) {
        forward.stream().filter(l -> canPushForward()).findFirst().orElseThrow().push(car);
    }

    public void pushReverse(Car car) {
        reverse.stream().filter(l -> canPushReverse()).findFirst().orElseThrow().push(car);
    }

    public void update() {
        Stream.of(forward, reverse)
                .flatMap(Collection::stream)
                .forEach(Lane::update);
    }
}
