package com.sample.coding.exercises.elevator.operation.controller;

import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPathException;
import com.sample.coding.exercises.elevator.operation.traversal.ElevatorTraversalPathService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultTraversalOperationTest {


    @Spy
    private DefaultTraversalOperation defaultTraversalOperation;

    @Spy
    private ElevatorTraversalPathService elevatorTraversalPathService;

    //@Test
    public void testMoveElevatorUpToTargetFloorSinglePath() throws InterruptedException {
        Elevator elevator = new Elevator(12);
        Assert.assertEquals(1, elevator.getCurrentFloor());
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Execute traversal
        defaultTraversalOperation.moveElevatorUpToTargetFloor(2, elevator, elevatorTraversalPath1);
        System.out.println("Current Elevator floor after move:> "+ elevator.getCurrentFloor());
        Assert.assertEquals(2, elevator.getCurrentFloor());
    }

    //@Test
    public void testMoveElevatorUpToTargetFloorMultiPath() throws InterruptedException, ElevatorTraversalPathException {
        Elevator elevator = new Elevator(12);
        Assert.assertEquals(1, elevator.getCurrentFloor());
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(2, 3, 4, 5, 6, 7, 8, 9, 10);
        ElevatorTraversalPath elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(4, 5, 6, 7, 8);

        // combine the two paths
        ElevatorTraversalPath newElevatorTraversalPath =  elevatorTraversalPathService.combineElevatorTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);

        // Execute traversal, we expect the elevator to stop on floor 2 to service main pickup passengers on primary traversal path
        defaultTraversalOperation.moveElevatorUpToTargetFloor(2, elevator, newElevatorTraversalPath);
        System.out.println("Current Elevator floor after move:> "+ elevator.getCurrentFloor());
        Assert.assertEquals(2, elevator.getCurrentFloor());

        System.out.println("\n");


        // Reset Elevator and travel paths to verify new behavior
        elevator = new Elevator(12);
        elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(5, 6, 7, 8, 9, 10);
        elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(4, 5, 6, 7, 8);
        newElevatorTraversalPath =  elevatorTraversalPathService.combineElevatorTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);

        // Execute traversal, we now expect the elevator to first stop on floor 4 to service main pickup passengers on primary traversal path before proceeding to 5
        defaultTraversalOperation.moveElevatorUpToTargetFloor(5, elevator, newElevatorTraversalPath);
        System.out.println("Current Elevator floor after move:> "+ elevator.getCurrentFloor());
        Assert.assertEquals(4, elevator.getCurrentFloor());
    }

    //@Test
    public void testMoveElevatorDownToTargetFloorSinglePath() throws InterruptedException {
        Elevator elevator = new Elevator(12);
        elevator.setCurrentFloor(10);
        Assert.assertEquals(10, elevator.getCurrentFloor());
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(6, 5, 4, 3);

        // Execute traversal
        defaultTraversalOperation.moveElevatorDownToTargetFloor(6, elevator, elevatorTraversalPath1);
        System.out.println("Current Elevator floor after move:> "+ elevator.getCurrentFloor());
        Assert.assertEquals(6, elevator.getCurrentFloor());
    }

    @Test
    public void testMoveElevatorDownToTargetFloorMultiPath() throws InterruptedException, ElevatorTraversalPathException {
        Elevator elevator = new Elevator(12);
        elevator.setCurrentFloor(10);
        Assert.assertEquals(10, elevator.getCurrentFloor());
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(6, 5, 4, 3, 2);
        ElevatorTraversalPath elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(8, 7, 6, 5, 4);

        // combine the two paths
        ElevatorTraversalPath newElevatorTraversalPath =  elevatorTraversalPathService.combineElevatorTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);

        // Execute traversal, we expect the elevator to stop on floor 2 to service main pickup passengers on primary traversal path
        defaultTraversalOperation.moveElevatorDownToTargetFloor(6, elevator, newElevatorTraversalPath);
        System.out.println("Current Elevator floor after move:> "+ elevator.getCurrentFloor());
        //Assert.assertEquals(8, elevator.getCurrentFloor());

        System.out.println("\n");


        // Reset Elevator and travel paths to verify new behavior
        elevator = new Elevator(12);
        elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(6, 5, 4, 3, 2);
        elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(8, 7, 6, 5, 4);
        newElevatorTraversalPath =  elevatorTraversalPathService.combineElevatorTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);

        // Execute traversal, we now expect the elevator to first stop on floor 4 to service main pickup passengers on primary traversal path before proceeding to 5
        defaultTraversalOperation.moveElevatorDownToTargetFloor(5, elevator, newElevatorTraversalPath);
        System.out.println("Current Elevator floor after move:> "+ elevator.getCurrentFloor());
        Assert.assertEquals(4, elevator.getCurrentFloor());
    }

}