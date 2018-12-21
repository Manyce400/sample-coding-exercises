package com.sample.coding.exercises.elevator.operation;

import com.sample.coding.exercises.elevator.model.ElevatorInstruction;
import com.sample.coding.exercises.elevator.model.ElevatorRunResult;

/**
 * Interface that defines the basic contract for the algorithm that will run an Elevator Mode
 *
 * @author manyce400
 */
public interface IOperationModeAlgo {


    /**
     * Executes algo implementation for an elevator run given instructions.
     *
     * @param currentElevatorFloor the current floor that the elevator is on when it receives instruction.
     * @param elevatorInstruction instructions to run a pickup and drop off implementation.
     * @return ElevatorRunResult returns run results
     */
    public ElevatorRunResult executeElevatorRun(int currentElevatorFloor, ElevatorInstruction elevatorInstruction);


}
