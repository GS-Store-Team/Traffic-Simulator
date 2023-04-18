package com.traffic_simulator.exceptions;

import lombok.Getter;

@Getter
public class SimulationException extends Exception {
    private Exception innerException;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public SimulationException(String message, Exception innerException) {
        super(message);
        this.innerException = innerException;
    }
}
