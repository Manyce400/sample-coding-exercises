package com.sample.coding.exercises.elevator.operation.controller;

import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;

/**
 * @author manyce400
 */
public interface ITraversalOperation {


    public void executeElevatorTraversalToDestinationFloor(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath);

}
