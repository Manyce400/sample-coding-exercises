package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class ITraversalModeStrategyTest {



    private LinkedList<Integer> traversalPath;

    @Spy
    private MockITraversalModeStrategy iTraversalModeStrategy;


    @Before
    public void beforeTest() {
        traversalPath = new LinkedList<>();
    }

    @Test
    public void testComputePathToDestinationFloorOnCurrentFloor() {
        iTraversalModeStrategy.computePathToDestinationFloor(4, 4, traversalPath);

        System.out.println("Floors{4 to 4} : traversalPath = " + traversalPath);

        Integer[] expectedTraversalPath = {};
        Integer[] actualTraversalPath = traversalPath.toArray(new Integer[0]);
        Assert.assertTrue(traversalPath.size() == 0);
    }

    @Test
    public void testComputePathToDestinationFloorAboveCurrentFloor() {
        iTraversalModeStrategy.computePathToDestinationFloor(1, 4, traversalPath);

        System.out.println("Floors{1 to 4} : traversalPath = " + traversalPath);

        Integer[] expectedTraversalPath = {2, 3, 4};
        Integer[] actualTraversalPath = traversalPath.toArray(new Integer[0]);

        Assert.assertTrue(traversalPath.size() == 3);
        Assert.assertArrayEquals(expectedTraversalPath, actualTraversalPath);
    }

    @Test
    public void testComputePathToDestinationFloorBelowCurrentFloor() {
        iTraversalModeStrategy.computePathToDestinationFloor(4, 1, traversalPath);

        System.out.println("Floors{4 to 1} : traversalPath = " + traversalPath);

        Integer[] expectedTraversalPath = {3, 2, 1};
        Integer[] actualTraversalPath = traversalPath.toArray(new Integer[0]);
        Assert.assertTrue(traversalPath.size() == 3);
        Assert.assertArrayEquals(expectedTraversalPath, actualTraversalPath);
    }



    private class MockITraversalModeStrategy implements ITraversalModeStrategy {

        public MockITraversalModeStrategy() {
        }

        @Override
        public ElevatorTraversalPath executeElevatorRun(int currentElevatorFloor, ElevatorOperationRequest elevatorOperationRequest) {
            throw new UnsupportedOperationException("");
        }

    }
}
