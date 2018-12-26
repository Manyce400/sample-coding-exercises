package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.operation.traversal.EfficientTraversalModeStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Assume an  Elevator only supports 12 floors total with the first floor being 1
 *
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class EfficientOperationModeAlgoTest {


    @InjectMocks
    private EfficientTraversalModeStrategy efficientOperationModeAlgo;


    @Test
    public void testIsPickupFloorsOnPrimaryRouteSameDirection() {
        int currentElevatorFloor = 1;
        ElevatorOperationRequest primaryElevatorOperationRequest = ElevatorOperationRequest.newInstance(5, 10);
        ElevatorOperationRequest secondaryElevatorOperationRequest = ElevatorOperationRequest.newInstance(2, 4);
        
//        boolean isPickupFloorsOnPrimaryRoute = efficientOperationModeAlgo.isPickupFloorsOnPrimaryRoute(currentElevatorFloor, primaryElevatorOperationRequest, secondaryElevatorOperationRequest);
//        System.out.println("isPickupFloorsOnPrimaryRoute = " + isPickupFloorsOnPrimaryRoute);
    }

    @Test
    public void testIsPickupFloorsOnPrimaryRouteDifferentDirection() {
        int currentElevatorFloor = 1;

        // primary elevator run request from floors 5 to 10
        ElevatorOperationRequest primaryElevatorOperationRequest = ElevatorOperationRequest.newInstance(5, 10);

        // secondary elevator run request from floors 3 to 1
        ElevatorOperationRequest secondaryElevatorOperationRequest = ElevatorOperationRequest.newInstance(3, 1);

        // Expecting true here since pickup floor of secondary instructions is on 3, elevator is
//        boolean isPickupFloorsOnPrimaryRoute = efficientOperationModeAlgo.isPickupFloorsOnPrimaryRoute(currentElevatorFloor, primaryElevatorOperationRequest, secondaryElevatorOperationRequest);
//        System.out.println("isPickupFloorsOnPrimaryRoute = " + isPickupFloorsOnPrimaryRoute);
    }

    
}


