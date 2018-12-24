package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.ElevatorDirectionE;
import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;

import java.util.LinkedList;

/**
 * Interface that defines the basic contract for the algorithm that will run an Elevator Mode
 *
 * @author manyce400
 */
public interface ITraversalModeStrategy {


    /**
     * Executes algo implementation for an elevator run given instructions.
     *
     * @param currentElevatorFloor the current floor that the elevator is on when it receives instruction.
     * @param elevatorOperationRequest instructions to run a pickup and drop off implementation.
     * @return ElevatorTraversalPath returns run results
     */
    public ElevatorTraversalPath executeElevatorRun(int currentElevatorFloor, ElevatorOperationRequest elevatorOperationRequest);


    default void computePathToDestinationFloor(int startFloor, int destinationFloor, LinkedList<Integer> traversalPath) {
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

    default ElevatorDirectionE calculateElevatorDirection(ElevatorOperationRequest elevatorOperationRequest) {
        int pickupFloor = elevatorOperationRequest.getPickupFloor();
        int dropOffFloor = elevatorOperationRequest.getDropOffFloor();

        if (pickupFloor < dropOffFloor) {
            return ElevatorDirectionE.Up;
        }

        return ElevatorDirectionE.Down;
    }

}
