package com.sample.coding.exercises.elevator.operation.controller;

import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;

/**
 * @author manyce400
 */
public interface ITraversalOperation {


    public void executeContinuousElevatorTraversal(Elevator elevator);

    public void executeElevatorTraversalToDestinationFloor(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath);

    public void addElevatorTraversalPath(final ElevatorTraversalPath elevatorTraversalPath);

}
