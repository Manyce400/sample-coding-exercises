package com.sample.coding.exercises.elevator.operation.controller;

import com.sample.coding.exercises.elevator.config.ElevatorConfiguration;
import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import com.sample.coding.exercises.elevator.operation.traversal.ElevatorTraversalPathService;
import com.sample.coding.exercises.elevator.operation.traversal.IElevatorTraversalPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author manyce400
 */
@Component(ElevatorOperationController.SPRING_BEAN)
public class ElevatorOperationController implements IElevatorOperationController {



    @Autowired
    @Qualifier(DefaultTraversalOperation.SPRING_BEAN)
    private ITraversalOperation iTraversalOperation;

    @Autowired
    @Qualifier(ElevatorTraversalPathService.SPRING_BEAN)
    private IElevatorTraversalPathService iElevatorTraversalPathService;

    @Autowired
    @Qualifier(ElevatorConfiguration.DEFAL_ELEVATOR_BEAN)
    private Elevator elevator ;


    public static final String SPRING_BEAN = "com.sample.coding.exercises.elevator.operation.controller.ElevatorOperationController";


    @Override
    public void acceptElevatorOperationRequest(ElevatorOperationRequest elevatorOperationRequest) {
        Assert.notNull(elevatorOperationRequest, "elevatorOperationRequest cannot be null");
        ElevatorTraversalPath elevatorTraversalPath = iElevatorTraversalPathService.computeElevatorTraversalPath(elevator, elevatorOperationRequest);
        System.out.println("adding to traversal list:>  "+ elevatorTraversalPath);
        iTraversalOperation.addElevatorTraversalPath(elevatorTraversalPath);
    }

}