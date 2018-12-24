package com.sample.coding.exercises.elevator.operation.traversal;

import com.sample.coding.exercises.elevator.model.*;

/**
 * @author manyce400
 */
public interface IElevatorTraversalPathService {

    public ElevatorTraversalPath computeElevatorTraversalPath(Elevator elevator, ElevatorOperationRequest elevatorOperationRequest);

    // Returns true if the 2 ElevatorTraversalPath can be
    public boolean canCombineTraversalPath(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath1, ElevatorTraversalPath elevatorTraversalPath2);

    // Combines the two traversal paths into a single traversal and throws exception if not possible.  Up to client to verify before calling
    public ElevatorTraversalPath combineElevatorTraversalPath(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath1, ElevatorTraversalPath elevatorTraversalPath2) throws ElevatorTraversalPathException;

    public ElevatorDirectionE computeElevatorTraversalPathDirection(ElevatorTraversalPath elevatorTraversalPath);

}
