package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;

/**
 * @author manyce400
 */
@Service(InEfficientTraversalModeStrategy.SPRING_BEAN)
public class InEfficientTraversalModeStrategy implements ITraversalModeStrategy {


    public static final String SPRING_BEAN = "com.sample.coding.exercises.elevator.operation.traversal.InEfficientTraversalModeStrategy";

    @Override
    public ElevatorTraversalPath executeElevatorRun(int currentElevatorFloor, ElevatorOperationRequest elevatorOperationRequest) {
        Assert.isTrue(currentElevatorFloor > 0, "Elevator current floor is not valid");
        Assert.notNull(elevatorOperationRequest, "elevatorOperationRequest cannot be null");

        // Build a LinkedList which will contain ordered traversal path
        LinkedList<Integer> traversalPath = new LinkedList<>();

        int pickupFloor = elevatorOperationRequest.getPickupFloor();
        int dropOffFloor = elevatorOperationRequest.getDropOffFloor();

        // Compute and populate the path to get to the pickup floor first
        computePathToDestinationFloor(currentElevatorFloor, pickupFloor, traversalPath);

        // Compute and populate the path to get to the drop off floor
        computePathToDestinationFloor(pickupFloor, dropOffFloor, traversalPath);

        int totalFloorsTraversed = traversalPath.size();

        ElevatorTraversalPath elevatorTraversalPath = new ElevatorTraversalPath(traversalPath);
        return elevatorTraversalPath;
    }


}
