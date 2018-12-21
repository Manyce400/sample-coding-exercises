package com.sample.coding.exercises.elevator.operation;

import com.sample.coding.exercises.elevator.model.ElevatorInstruction;
import com.sample.coding.exercises.elevator.model.ElevatorRunResult;
import org.springframework.util.Assert;

import java.util.LinkedList;

/**
 * @author manyce400
 */
public class InEfficientOperationModeAlgo implements IOperationModeAlgo {


    @Override
    public ElevatorRunResult executeElevatorRun(int currentElevatorFloor, ElevatorInstruction elevatorInstruction) {
        Assert.isTrue(currentElevatorFloor > 0, "Elevator current floor is not valid");
        Assert.notNull(elevatorInstruction, "elevatorInstruction cannot be null");

        // Build a LinkedList which will contain ordered traversal path
        LinkedList<Integer> traversalPath = new LinkedList<>();

        int pickupFloor = elevatorInstruction.getPickupFloor();
        int dropOffFloor = elevatorInstruction.getDropOffFloor();

        // Compute and populate the path to get to the pickup floor first
        computePathToDestinationFloor(currentElevatorFloor, pickupFloor, traversalPath);

        // Compute and populate the path to get to the drop off floor
        computePathToDestinationFloor(pickupFloor, dropOffFloor, traversalPath);

        int totalFloorsTraversed = traversalPath.size();

        ElevatorRunResult elevatorRunResult = new ElevatorRunResult(totalFloorsTraversed, traversalPath);
        return elevatorRunResult;
    }


    void computePathToDestinationFloor(int startFloor, int destinationFloor, LinkedList<Integer> traversalPath) {
        if(destinationFloor > startFloor) {
            // We need to move up to the pickup floor destination
            // Skip the current floor in the path when moving up
            startFloor++;
            for(int i = startFloor; i <= destinationFloor; i++) {
                traversalPath.add(i);
            }
        } else if(destinationFloor < startFloor) {
            // We need to move down to the pickup floor destination
            // Skip the current floor in the path when moving down
            startFloor--;
            for(int i = startFloor; i >= destinationFloor; i--) {
                traversalPath.add(i);
            }
        } else {
            // TODO refactor and have first
            // Base case
            // Elevator current floor is the same as the pickup floor, no further traversal required.
            //traversalPath.add(destinationFloor);
        }
    }


}
