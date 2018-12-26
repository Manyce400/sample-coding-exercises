package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class InEfficientOperationModeAlgoTest {


    @InjectMocks
    private InEfficientTraversalModeStrategy inEfficientOperationModeAlgo;


    @Test
    public void testComputeElevatorRunTraversalUpToDown() {
        ElevatorOperationRequest elevatorOperationRequest = new ElevatorOperationRequest(4, 1);
        ElevatorTraversalPath elevatorTraversalPath = inEfficientOperationModeAlgo.executeElevatorRun(1, elevatorOperationRequest);
        System.out.println("elevatorTraversalPath = " + elevatorTraversalPath);

        Integer[] expectedTraversalPath = {4, 3, 2, 1};
        Integer[] actualTraversalPath = elevatorTraversalPath.getFloorTraversalPath().toArray(new Integer[0]);

        Assert.assertTrue(actualTraversalPath.length == 4);
        Assert.assertArrayEquals(expectedTraversalPath, actualTraversalPath);
    }


    @Test
    public void testComputeElevatorRunTraversalDownToUp() {
        ElevatorOperationRequest elevatorOperationRequest = new ElevatorOperationRequest(4, 10);
        ElevatorTraversalPath elevatorTraversalPath = inEfficientOperationModeAlgo.executeElevatorRun(1, elevatorOperationRequest);
        System.out.println("elevatorTraversalPath = " + elevatorTraversalPath);

        Integer[] expectedTraversalPath = {4, 5, 6, 7, 8, 9, 10};
        Integer[] actualTraversalPath = elevatorTraversalPath.getFloorTraversalPath().toArray(new Integer[0]);
        Assert.assertTrue(actualTraversalPath.length == 7);
        Assert.assertArrayEquals(expectedTraversalPath, actualTraversalPath);
    }

}
