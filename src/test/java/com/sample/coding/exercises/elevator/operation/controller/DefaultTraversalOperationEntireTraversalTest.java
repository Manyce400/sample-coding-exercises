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

import java.util.LinkedList;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultTraversalOperationEntireTraversalTest {



    @Spy
    private ElevatorTraversalPathService elevatorTraversalPathService;

    @Spy
    private DefaultTraversalOperation defaultTraversalOperation;


    //@Test
    public void testExecuteElevatorTraversalToDestinationFloorUpSinglePath() {
        Elevator elevator = new Elevator(12);
        Assert.assertEquals(1, elevator.getCurrentFloor());
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Execute traversal to destination floor
        defaultTraversalOperation.executeElevatorTraversalToDestinationFloor(elevator, elevatorTraversalPath1);

        // All floors elevator stopped on during this traversal
        LinkedList<Integer> stopFloorsOnTraversalPath = elevatorTraversalPath1.getStopFloorsOnTraversalPath();

        // We expect current elevator floor to be on 10 since pickup was done on 2 and drop off on 10
        Assert.assertEquals(10, elevator.getCurrentFloor());

        // Stop floors should only be 2 and 10
        Assert.assertTrue(stopFloorsOnTraversalPath.size() == 2);
        Assert.assertTrue(stopFloorsOnTraversalPath.contains(2));
        Assert.assertTrue(stopFloorsOnTraversalPath.contains(10));
    }

    @Test
    public void testExecuteElevatorTraversalToDestinationFloorUpMultiPath() throws ElevatorTraversalPathException {
        Elevator elevator = new Elevator(12);
        Assert.assertEquals(1, elevator.getCurrentFloor());
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(2, 3, 4, 5, 6, 7, 8, 9, 10);
        ElevatorTraversalPath elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(4, 5, 6, 7, 8);

        ElevatorTraversalPath combinedElevatorTraversalPath =  elevatorTraversalPathService.combineElevatorTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);

        // Execute traversal to destination floor
        defaultTraversalOperation.executeElevatorTraversalToDestinationFloor(elevator, combinedElevatorTraversalPath);

        // All floors elevator stopped on during this traversal
        LinkedList<Integer> stopFloorsOnTraversalPath = combinedElevatorTraversalPath.getStopFloorsOnTraversalPath();

        // We expect current elevator floor to be on 10 since pickup was done on 2 and drop off on 10
        Assert.assertEquals(10, elevator.getCurrentFloor());

        // Stop floors should only be 2, 4, 8, 10
        Assert.assertTrue(stopFloorsOnTraversalPath.contains(2));
        Assert.assertTrue(stopFloorsOnTraversalPath.contains(4));
        Assert.assertTrue(stopFloorsOnTraversalPath.contains(8));
        Assert.assertTrue(stopFloorsOnTraversalPath.contains(10));
    }

}
