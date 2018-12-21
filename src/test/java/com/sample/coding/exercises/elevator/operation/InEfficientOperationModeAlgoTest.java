package com.sample.coding.exercises.elevator.operation;

import com.sample.coding.exercises.elevator.model.ElevatorInstruction;
import com.sample.coding.exercises.elevator.model.ElevatorRunResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class InEfficientOperationModeAlgoTest {

    
    
    private LinkedList<Integer> traversalPath;

    @InjectMocks
    private InEfficientOperationModeAlgo inEfficientOperationModeAlgo;
    
    @Before
    public void beforeTest() {
        traversalPath = new LinkedList<>();
    }

    @Test
    public void testComputePathToDestinationFloorOnCurrentFloor() {
        inEfficientOperationModeAlgo.computePathToDestinationFloor(4, 4, traversalPath);

        System.out.println("Floors{4 to 4} : traversalPath = " + traversalPath);

        Integer[] expectedTraversalPath = {4};
        Integer[] actualTraversalPath = traversalPath.toArray(new Integer[0]);
        //Assert.assertTrue(traversalPath.size() == 1);
        //Assert.assertArrayEquals(expectedTraversalPath, actualTraversalPath);
    }

    @Test
    public void testComputePathToDestinationFloorAboveCurrentFloor() {
        inEfficientOperationModeAlgo.computePathToDestinationFloor(1, 4, traversalPath);

        System.out.println("Floors{1 to 4} : traversalPath = " + traversalPath);

//        Integer[] expectedTraversalPath = {1, 2, 3, 4};
//        Integer[] actualTraversalPath = traversalPath.toArray(new Integer[0]);
//        Assert.assertTrue(traversalPath.size() == 4);
//        Assert.assertArrayEquals(expectedTraversalPath, actualTraversalPath);
    }

    @Test
    public void testComputePathToDestinationFloorBelowCurrentFloor() {
        inEfficientOperationModeAlgo.computePathToDestinationFloor(4, 1, traversalPath);

        System.out.println("Floors{4 to 1} : traversalPath = " + traversalPath);

//        Integer[] expectedTraversalPath = {4, 3, 2, 1};
//        Integer[] actualTraversalPath = traversalPath.toArray(new Integer[0]);
//        Assert.assertTrue(traversalPath.size() == 4);
//        Assert.assertArrayEquals(expectedTraversalPath, actualTraversalPath);
    }

    @Test
    public void testComputeElevatorRunTraversal() {
        ElevatorInstruction elevatorInstruction = new ElevatorInstruction(4, 1);
        ElevatorRunResult elevatorRunResult = inEfficientOperationModeAlgo.executeElevatorRun(1, elevatorInstruction);
        System.out.println("elevatorRunResult = " + elevatorRunResult);
    }

}
