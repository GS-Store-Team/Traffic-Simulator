package com.traffic_simulator.utils;

import com.traffic_simulator.models.Point;
import com.traffic_simulator.simulation.models.Coordinate;

public class Utils {

    private static final double LANE_WIDTH = 5;
    public static Coordinate[] computePoints(Coordinate start, Coordinate end, boolean isForward, int laneIndex) {

        Coordinate[] pair = new Coordinate[2];

        double cos = computeCos(start, end);
        double sin = computeSin(start, end);

        if (isForward) {
            pair[0] = new Coordinate(start.x() + cos * laneIndex * LANE_WIDTH + LANE_WIDTH / 2, start.y() + sin * laneIndex * LANE_WIDTH + LANE_WIDTH / 2);
            pair[1] = new Coordinate(end.x() + cos * laneIndex * LANE_WIDTH + LANE_WIDTH / 2, end.y() + sin * laneIndex * LANE_WIDTH + LANE_WIDTH / 2);
            return pair;
        }

        pair[0] = new Coordinate(end.x() + cos * laneIndex * LANE_WIDTH + LANE_WIDTH / 2, end.y() + sin * laneIndex * LANE_WIDTH + LANE_WIDTH / 2);
        pair[1] = new Coordinate(start.x() + cos * laneIndex * LANE_WIDTH + LANE_WIDTH / 2, start.y() + sin * laneIndex * LANE_WIDTH + LANE_WIDTH / 2);
        return pair;
    }

    public static double computeSin(Coordinate start, Coordinate end) {
        return (end.y() - start.y()) / Math.sqrt(Math.pow(end.x() - start.x(), 2) + Math.pow(end.y() - start.y(), 2));
    }

    public static double computeCos(Coordinate start, Coordinate end) {
        return Math.sqrt(1 - Math.pow(computeSin(start, end), 2));
    }
}
