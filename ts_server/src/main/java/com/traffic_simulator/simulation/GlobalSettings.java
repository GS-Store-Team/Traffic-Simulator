package com.traffic_simulator.simulation;

import lombok.Data;

@Data
public class GlobalSettings {

    public static final int ticksPerSecond = 10;
    public static final int buildingConnectionRadius = 60;
    public static final int buildingWidth = 50;
    public static final int cellWidth = 4;
    public static final int cellLength = 1;

    public static final double cellTrafficWeightModifier = 1;
    public static final double cellNaturalWeightModifier = 1;
    public static final double crossroadCellNaturalWeightModifier = 0.5;

    public static final int automobileLength = 5;
    public static final int truckLength = 10;

}