package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.ElevatorDirectionE;
import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service(EfficientTraversalModeStrategy.SPRING_BEAN)
public class EfficientTraversalModeStrategy implements ITraversalModeStrategy {



    public static final String SPRING_BEAN = "com.sample.coding.exercises.elevator.operation.traversal.EfficientTraversalModeStrategy";


    @Override
    public ElevatorTraversalPath executeElevatorRun(int currentElevatorFloor, ElevatorOperationRequest elevatorOperationRequest) {
        return null;
    }



    /**
     * From the list of all ElevatorOperationRequest, this will return a new list of instructions that can be serviced with the first request
     * on this list.  These instructions will be removed from the original list of instructions.
     */
    private LinkedList<ElevatorOperationRequest>  getInstructionsThatCanBeServicedWithFirst(int currentElevatorFloor, LinkedList<ElevatorOperationRequest> elevatorOperationRequests) {
        LinkedList<ElevatorOperationRequest> instructionsOnSameRoute = new LinkedList<>();

        // Build a LinkedList which will contain ordered traversal path
        LinkedList<Integer> traversalPath = new LinkedList<>();

        // First instruction traversal path
        ElevatorOperationRequest elevatorOperationRequest = elevatorOperationRequests.removeFirst();
        int pickupFloor = elevatorOperationRequest.getPickupFloor();
        int dropOffFloor = elevatorOperationRequest.getDropOffFloor();

        // Compute and populate the path to get to the pickup floor first
        computePathToDestinationFloor(currentElevatorFloor, pickupFloor, traversalPath);

        // Compute and populate the path to get to the drop off floor
        computePathToDestinationFloor(pickupFloor, dropOffFloor, traversalPath);

        // Now for all remaining instructions find instructions that can be service
        return null;

    }


    private boolean isElevatorInstructionCompatible(int currentElevatorFloor, ElevatorOperationRequest primaryElevatorOperationRequest, ElevatorOperationRequest secondaryElevatorOperationRequest) {
        int pickupFloor = primaryElevatorOperationRequest.getPickupFloor();
        int dropOffFloor = primaryElevatorOperationRequest.getDropOffFloor();

        ElevatorDirectionE primaryElevatorDirectionE = calculateElevatorDirection(primaryElevatorOperationRequest);
        ElevatorDirectionE secondaryElevatorDirectionE = calculateElevatorDirection(primaryElevatorOperationRequest);

        // Base case, eliminate the secondary instruction if it requires a different travel direction
        if(primaryElevatorDirectionE != secondaryElevatorDirectionE) {
            return false;
        }

        // Even though direction might be the same, eliminate if the floor pickup floor for secondary instruction is not on primary route
        boolean isPickupFloorsOnPrimaryRoute = isPickupFloorsOnPrimaryRoute(currentElevatorFloor, primaryElevatorOperationRequest, secondaryElevatorOperationRequest);
        if(!isPickupFloorsOnPrimaryRoute) {
            return false;
        }

        return true;
    }


    boolean isPickupFloorsOnPrimaryRoute(int currentElevatorFloor, ElevatorOperationRequest primaryElevatorOperationRequest, ElevatorOperationRequest secondaryElevatorOperationRequest) {
        int primaryPickupFloor = primaryElevatorOperationRequest.getPickupFloor();
        int secondaryPickupFloor = secondaryElevatorOperationRequest.getPickupFloor();
        ElevatorDirectionE primaryElevatorDirectionE = calculateElevatorDirection(primaryElevatorOperationRequest);

        boolean onRoute = false;
        switch (primaryElevatorDirectionE) {
            case Up:
                onRoute = currentElevatorFloor <= secondaryPickupFloor;
                break;
            case Down:
                onRoute = currentElevatorFloor >= secondaryPickupFloor;
        }


        return onRoute;
    }



}
