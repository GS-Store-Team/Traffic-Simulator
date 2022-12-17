package com.traffic_simulator.exceptions;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class InvalidMapException extends Exception {
    private List<GraphObject> invalidGraphObjects;
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
        this.invalidNodes = invalidNodes;
        this.invalidGraphObjects = new ArrayList<>();
        for (Node node : invalidNodes) {
            this.invalidGraphObjects.add(node.getRefGraphObject());
        }
    }
}
