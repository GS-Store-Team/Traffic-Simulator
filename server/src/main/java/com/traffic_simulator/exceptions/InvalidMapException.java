package com.traffic_simulator.exceptions;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.models.MapObject;

import java.util.List;

public class InvalidMapException extends Exception {
    private List<MapObject> invalidMapObjects;
    private List<Node> invalidNodes;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidMapException(String message, List<Node> invalidNodes) {
        super(message);
//        this.invalidNodes = invalidNodes;
//        this.invalidMapObjects = new ArrayList<>();
//        for (Node node : invalidNodes) {
//            this.invalidMapObjects.add(node.getRefMapObject());
//        }
    }

    @Override
    public String toString() {
        return "InvalidMapException{" +
                "invalidMapObjects=" + invalidMapObjects +
                ", invalidNodes=" + invalidNodes +
                '}';
    }
}
