package com.traffic_simulator.exceptions;

import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import lombok.Getter;

import java.util.List;

@Getter
public class GraphConstructionException extends Exception {
    private List<NodeNe> unreachableNodes;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public GraphConstructionException(String message, List<NodeNe> unreachableNodes) {
        super(message);
        this.unreachableNodes = unreachableNodes;
    }
}
