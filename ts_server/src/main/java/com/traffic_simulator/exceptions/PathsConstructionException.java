package com.traffic_simulator.exceptions;

import com.traffic_simulator.businnes_logic.simulation_runner.algorithms.graph.graph_elements.Node;
import lombok.Getter;

import java.util.List;

@Getter
public class PathsConstructionException extends Exception {
    private List<Node> unreachableNodes;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PathsConstructionException(String message, List<Node> unreachableNodes) {
        super(message);
        this.unreachableNodes = unreachableNodes;
    }
}
