package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPathException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class ElevatorTraversalPathServiceTest {


    @Mock
    private ITraversalModeStrategy iTraversalModeStrategy;

    @InjectMocks
    private ElevatorTraversalPathService elevatorTraversalPathService;



    // Test the base case when the 2 paths are in a different direction.
    @Test
    public void testCanCombineTraversalPathDifferentDirections() {
        Elevator elevator = new Elevator(12);

        // path1 requires pickup from 2 and drop off on 10. Up path.
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(2, 3, 4, 5, 6, 7, 8, 9, 10);

        // path2 requires pickup from 6 and drop off on 3. Down path.
        ElevatorTraversalPath elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(6, 5, 4, 3);

        boolean canCombineTraversalPath = elevatorTraversalPathService.canCombineTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);
        System.out.println("canCombineTraversalPath = " + canCombineTraversalPath);
        Assert.assertFalse(canCombineTraversalPath);
    }

    @Test
    public void testCanCombineTraversalPathUp() {
        Elevator elevator = new Elevator(12);
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(2, 3, 4, 5, 6, 7, 8, 9, 10);
        ElevatorTraversalPath elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(4, 5, 6, 7, 8);

        boolean canCombineTraversalPath = elevatorTraversalPathService.canCombineTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);
        System.out.println("canCombineTraversalPath = " + canCombineTraversalPath);
        Assert.assertTrue(canCombineTraversalPath);
    }

    @Test
    public void testCanCombineTraversalPathDown() {
        Elevator elevator = new Elevator(12);
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(9, 8, 7, 6, 5, 4, 3);
        ElevatorTraversalPath elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(6, 5, 4, 3);

        boolean canCombineTraversalPath = elevatorTraversalPathService.canCombineTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);
        System.out.println("canCombineTraversalPath = " + canCombineTraversalPath);
        Assert.assertTrue(canCombineTraversalPath);
    }
    
    
    @Test
    public void testCombineElevatorTraversalPath() throws ElevatorTraversalPathException {
        Elevator elevator = new Elevator(12);
        ElevatorTraversalPath elevatorTraversalPath1 = ElevatorTraversalPath.newInstance(2, 3, 4, 5, 6, 7, 8, 9, 10);
        ElevatorTraversalPath elevatorTraversalPath2 = ElevatorTraversalPath.newInstance(4, 5, 6, 7, 8);
        
        // combine the two paths
        ElevatorTraversalPath newElevatorTraversalPath =  elevatorTraversalPathService.combineElevatorTraversalPath(elevator, elevatorTraversalPath1, elevatorTraversalPath2);
        System.out.println("newElevatorTraversalPath = " + newElevatorTraversalPath);
    }

}
