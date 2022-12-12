package com.traffic_simulator.businnes_logic;

import lombok.Data;

@Data
public class GlobalSettings {

    public static int cellWidth = 4;
    public static int cellLength = 1;

    public static double cellTrafficWeightModifier = 1;
    public static double cellNaturalWeightModifier = 1;
    public static double crossroadCellNaturalWeightModifier = 0.5;

    public static int automobileLength = 5;
    public static double automobileMaxAcceleration = 3;
    public static double automobileMinAcceleration = -10;
    public static double truckMaxAcceleration = 3;
    public static double truckMinAcceleration = -8;

}
