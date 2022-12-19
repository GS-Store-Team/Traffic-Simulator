package com.traffic_simulator.simulation;

import com.traffic_simulator.simulation.models.buildings.Building;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.models.supportive.Coordinates;

public class MyVectorGeometry {
    /**
     * Compute vector coordinates using its ends coordinates, which made of starting and ending points of the road.
     * For intermediate calculations.
     * @param road
     * @return
     */
    public static Coordinates computeRoadVectorCoordinates(Road road) {
        return new Coordinates(
                road.getEndPoint().getCoordinates().getX() - road.getStartPoint().getCoordinates().getX(),
                road.getEndPoint().getCoordinates().getY() - road.getStartPoint().getCoordinates().getY());
    }

    /**
     * Compute vector coordinates using its ends coordinates.
     * For intermediate calculations.
     * @param start
     * @param end
     * @return
     */
    public static Coordinates computeVectorCoordinates(Coordinates start, Coordinates end) {
        return new Coordinates(
                end.getX()  - start.getX(),
                end.getY() - start.getY());
    }

    /**
     * Compute vector absolute length using its ends coordinates, which made of starting and ending points of the road.
     * For intermediate calculations.
     * @param road
     * @return
     */
    public static double computeRoadVectorLength(Road road) {
        return (int) Math.sqrt(
                Math.pow(computeRoadVectorCoordinates(road).getX(), 2) +
                        Math.pow(computeRoadVectorCoordinates(road).getY(), 2)
        );
    }

    /**
     * Compute vector absolute length using its ends coordinates. For intermediate calculations.
     * @param start
     * @param end
     * @return
     */
    public static double computeVectorLength(Coordinates start, Coordinates end) {
        return (int) Math.sqrt(
                Math.pow(computeVectorCoordinates(start, end).getX(), 2) +
                        Math.pow(computeVectorCoordinates(start, end).getY(), 2)
        );
    }

    /**
     * Compute vector absolute length. For intermediate calculations.
     * @param v1 vector
     * @return length
     */
    public static double computeVectorLength(Coordinates v1) {
        return (int) Math.sqrt(
                Math.pow(v1.getX(), 2) +
                        Math.pow(v1.getY(), 2)
        );
    }

    /**
     * Compute scalar product. For intermediate calculations.
     * @param vector1 vector1
     * @param vector2 vector2
     * @return scalar product
     */
    public static double computeScalarProduct(Coordinates vector1, Coordinates vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }

    /**
     * Compute cosine of the angle between two roads (their vectors).
     * @param road1 road1 object
     * @param road2 road2 object
     * @return cosine in range from 1.0 (0) to 1.0 (180)
     */
    public static double computeRoadsAngleCos(Road road1, Road road2) {
        return computeScalarProduct(computeRoadVectorCoordinates(road1), computeRoadVectorCoordinates(road2)) /
                (computeRoadVectorLength(road1) * computeRoadVectorLength(road2));
    }

    /**
     * Compute cosine of the angle between two arbitrary vectors.
     * @param v1 vector1 coordinates
     * @param v2 vector2 coordinates
     * @return cosine in range from 1.0 (0) to 1.0 (180)
     */
    public static double computeVectorsAngleCos(Coordinates v1, Coordinates v2) {
        return computeScalarProduct(v1, v2) /
                (computeVectorLength(v1) * computeVectorLength(v2));
    }

    /**
     * Check if point was placed somewhere on the road.
     * @param point point coordinates
     * @param road road object
     * @return true if its on road line between its ends, false otherwise
     */
    public static boolean pointIsOnRoad(Coordinates point, Road road) {
        if (MyVectorGeometry.computeVectorsAngleCos(
                MyVectorGeometry.computeVectorCoordinates(road.getStartPoint().getCoordinates(), point),
                MyVectorGeometry.computeRoadVectorCoordinates(road)) == 1) {
            return road.getStartPoint().getCoordinates().getX() < point.getX() & point.getX() < road.getEndPoint().getCoordinates().getX() ||
                    road.getEndPoint().getCoordinates().getX() < point.getX() & point.getX() < road.getStartPoint().getCoordinates().getX();
        }

        return false;
    }

    /**
     * Check if point is located in building connection circle.
     * @param point point coordinates
     * @param building building object
     * @return true if its in circle, false otherwise
     */
    public static boolean pointIsInBuildingCircleArea(Coordinates point, Building building) {
        return computeVectorLength(point, building.getCenter()) <= GlobalSettings.buildingConnectionRadius;
    }
}
