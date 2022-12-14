package com.traffic_simulator.exceptions;

import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.MapObject;
import com.traffic_simulator.simulation.graph.graph_elements.Node;

import java.util.ArrayList;
import java.util.List;

public class InvalidMapException extends Exception {
    private List<MapObject> invalidMapObjects;
    private List<NodeNe> invalidNodes;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidMapException(String message, List<NodeNe> invalidNodes) {
        super(message);
//        this.invalidNodes = invalidNodes;
//        this.invalidMapObjects = new ArrayList<>();
//        for (Node node : invalidNodes) {
//            this.invalidMapObjects.add(node.getRefMapObject());
//        }
    }
}
