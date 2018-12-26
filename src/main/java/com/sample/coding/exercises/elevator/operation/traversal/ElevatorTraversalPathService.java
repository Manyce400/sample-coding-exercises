package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author manyce400
 */
@Service(ElevatorTraversalPathService.SPRING_BEAN)
public class ElevatorTraversalPathService implements IElevatorTraversalPathService {



    @Autowired
    @Qualifier(InEfficientTraversalModeStrategy.SPRING_BEAN)
    private ITraversalModeStrategy iTraversalModeStrategy;

    public static final String SPRING_BEAN = "com.sample.coding.exercises.elevator.operation.traversal.ElevatorTraversalPathService";



    @Override
    public ElevatorTraversalPath computeElevatorTraversalPath(Elevator elevator, ElevatorOperationRequest elevatorOperationRequest) {
        Assert.notNull(elevator, "elevator cannot be null");
        Assert.notNull(elevatorOperationRequest, "elevatorOperationRequest cannot be null");

        ElevatorTraversalPath elevatorTraversalPath = iTraversalModeStrategy.executeElevatorRun(elevator.getCurrentFloor(), elevatorOperationRequest);

        return elevatorTraversalPath;
    }

    @Override
    public boolean canCombineTraversalPath(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath1, ElevatorTraversalPath elevatorTraversalPath2) {
        Assert.notNull(elevator, "elevator cannot be null");
        Assert.notNull(elevatorTraversalPath1, "elevatorTraversalPath1 cannot be null");
        Assert.notNull(elevatorTraversalPath2, "elevatorTraversalPath2 cannot be null");

        // Traversal path can only be combined if Elevator is operating efficiently
        if(elevator.getElevatorOperationModeE() == ElevatorOperationModeE.InEfficientMode) {
            return false;
        }


        // Base case if the traversal direction of both paths are different the 2 paths cant be combined
        ElevatorDirectionE elevatorDirectionE1 = computeElevatorTraversalPathDirection(elevatorTraversalPath1);
        ElevatorDirectionE elevatorDirectionE2 = computeElevatorTraversalPathDirection(elevatorTraversalPath2);

        if(elevatorDirectionE1 != elevatorDirectionE2) {
            // two paths are in a different direction and cant be combined
            return false;
        }

        // All that matters now, check pickup floors for path2 relative to current elevator floor to determine if primary path can accept secondary
        int currentElevatorFloor = elevator.getCurrentFloor();
        int path1PickUpFloor = elevatorTraversalPath1.getFloorTraversalPath().getFirst();
        int path2PickUpFloor = elevatorTraversalPath2.getFloorTraversalPath().getFirst();

        boolean canCombine = false;
        switch (elevatorDirectionE1) {
            case Up:
                canCombine = currentElevatorFloor <= path2PickUpFloor;
                break;
            case Down:
                if(currentElevatorFloor > path1PickUpFloor) {
                    // Check If the current elevator floor is greater than path2 pickup floor.  This is because if it is we will have to go up
                    // to pick up path2 passenger which will break the flow of efficiency
                    canCombine = currentElevatorFloor >= path2PickUpFloor;
                } else {
                    // IF the elevator current flow is lower than path1PickUpFloor then elevator will have to travel up to pickup path1 before traveling down
                    // Current Elevator floor will have to start from path1PickUpFloor
                    canCombine = path1PickUpFloor  >= path2PickUpFloor;
                }
             default:
                break;
        }

        return canCombine;
    }

    @Override
    public ElevatorTraversalPath combineElevatorTraversalPath(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath1, ElevatorTraversalPath elevatorTraversalPath2) throws ElevatorTraversalPathException {
        boolean canCombineTraversalPath = canCombineTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);

        if(!canCombineTraversalPath) {
            throw new ElevatorTraversalPathException("Provided elevatorTraversalPath1 and elevatorTraversalPath2 cannot be combined");
        }

        // Combine the 2 into a single traversal path plan.
        elevatorTraversalPath1.mergeFloorTraversalPath(elevatorTraversalPath2.getFloorTraversalPath());
        return elevatorTraversalPath1;
    }

    @Override
    public ElevatorDirectionE computeElevatorTraversalPathDirection(ElevatorTraversalPath elevatorTraversalPath) {
        Assert.notNull(elevatorTraversalPath, "elevatorTraversalPath cannot be null");

        // Get the current pickup floor and final floor which will be the drop off to compute traversal direction.
        int pickupFloor = elevatorTraversalPath.getFloorTraversalPath().getFirst();
        int dropOffFloor = elevatorTraversalPath.getFloorTraversalPath().getLast();

        if (pickupFloor < dropOffFloor) {
            return ElevatorDirectionE.Up;
        }

        return ElevatorDirectionE.Down;
    }
}