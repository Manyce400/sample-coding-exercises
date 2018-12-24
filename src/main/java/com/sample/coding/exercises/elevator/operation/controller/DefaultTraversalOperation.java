package com.sample.coding.exercises.elevator.operation.controller;

import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author manyce400
 */
@Service(DefaultTraversalOperation.SPRING_BEAN)
public class DefaultTraversalOperation implements ITraversalOperation {



    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private static final int MOVE_OPERATION_DELAY = 2;

    public static final String SPRING_BEAN = "com.sample.coding.exercises.elevator.operation.controller.DefaultTraversalOperation";

    @Override
    public void executeElevatorTraversalToDestinationFloor(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath) {
        Assert.notNull(elevator, "elevator cannot be null");
        Assert.notNull(elevatorTraversalPath, "elevatorTraversalPath cannot be null");
        Assert.isTrue(elevatorTraversalPath.getFloorTraversalPath().size() > 0, "");

        try {
            lock.writeLock().lock();
            int currentFloor = elevator.getCurrentFloor();
            int targetFloor = elevatorTraversalPath.getFloorTraversalPath().getFirst();

            if(currentFloor < targetFloor) {
                moveElevatorUpToTargetFloor(targetFloor, elevator, elevatorTraversalPath);
            }  else if(currentFloor > targetFloor) {
                moveElevatorDownToTargetFloor(targetFloor, elevator, elevatorTraversalPath);
            }

            // Remove the first floor on the traversal path since move/traversal operation is now complete
            //elevatorTraversalPath.getFloorTraversalPath().removeFirst();

            System.out.println("Completed move operation, stopped on floor:  "+ targetFloor);
        } catch (InterruptedException e) {
            System.out.println("Exception occurred while elevator was moving to target floor: " + e);
        } finally {
            lock.writeLock().unlock();
        }
    }


    void moveElevatorUpToTargetFloor(int targetFloor, Elevator elevator, ElevatorTraversalPath elevatorTraversalPath) throws InterruptedException {
        int currentFloor = elevator.getCurrentFloor();

        for(int i = currentFloor; i<= targetFloor; i++) {
            System.out.println("Executing Elevator move up operation, currently on floor:=> "+i);

            // Update Elevator details to reflect changes
            elevator.setCurrentFloor(i);

            // Sleep to simulate time taken by physical movement of elevator to a floor
            TimeUnit.SECONDS.sleep(MOVE_OPERATION_DELAY);

            // Check to see if a stop needs to be made on this floor due to a combined traversal path
            boolean stopOnFloor = elevatorTraversalPath.isAdditionalFloorTraversalPathFloorStop(i);
            if(stopOnFloor) {
                // Stop on this floor.  Break out and return control so new traversals can be executed from this point.
                System.out.println("Making a stop on this floor to pickup additional riders traveling along this path");
                break;
            }
        }
    }

    void moveElevatorDownToTargetFloor(int targetFloor, Elevator elevator, ElevatorTraversalPath elevatorTraversalPath) throws InterruptedException {
        int currentFloor = elevator.getCurrentFloor();

        for(int i = currentFloor; i >= targetFloor; i--) {
            System.out.println("Executing Elevator move down operation, currently on floor:=> "+i);

            // Update Elevator details to reflect changes
            elevator.setCurrentFloor(i);

            // Sleep to simulate time taken by physical movement of elevator to a floor
            TimeUnit.SECONDS.sleep(MOVE_OPERATION_DELAY);

            // Check to see if a stop needs to be made on this floor due to a combined traversal path
            boolean stopOnFloor = elevatorTraversalPath.isAdditionalFloorTraversalPathFloorStop(i);
            if(stopOnFloor) {
                // Stop on this floor.  Break out and return control so new traversals can be executed from this point.
                System.out.println("Making a stop on this floor to pickup additional riders traveling along this path");
                break;
            }
        }
    }


}
