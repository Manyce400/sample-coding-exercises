package com.sample.coding.exercises.elevator.model;

/**
 * @author manyce400
 */
public class ElevatorTraversalPathException extends Exception {

    public ElevatorTraversalPathException() {
    }

    public ElevatorTraversalPathException(String message) {
        super(message);
    }

    public ElevatorTraversalPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElevatorTraversalPathException(Throwable cause) {
        super(cause);
    }

    public ElevatorTraversalPathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
