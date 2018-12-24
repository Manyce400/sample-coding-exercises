package com.sample.coding.exercises.elevator.operation.controller;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.sample.coding.exercises.elevator.config.ThreadPoolConfiguration;
import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import com.sample.coding.exercises.elevator.operation.traversal.ElevatorTraversalPathService;
import com.sample.coding.exercises.elevator.operation.traversal.IElevatorTraversalPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author manyce400
 */
@Component(ElevatorOperationController.SPRING_BEAN)
public class ElevatorOperationController implements IElevatorOperationController, Runnable {



    @Autowired
    @Qualifier(ThreadPoolConfiguration.ELEVATOR_OPS_THREAD_POOL)
    private ListeningExecutorService listeningExecutorService;

    @Autowired
    @Qualifier(DefaultTraversalOperation.SPRING_BEAN)
    private ITraversalOperation iTraversalOperation;

    @Autowired
    @Qualifier(ElevatorTraversalPathService.SPRING_BEAN)
    private IElevatorTraversalPathService iElevatorTraversalPathService;

    // Create a new instance of an Elevator
    Elevator elevator = new Elevator(12);

    private BlockingQueue<ElevatorOperationRequest> elevatorOperationRequests = new LinkedBlockingQueue<>();

    private BlockingQueue<ElevatorTraversalPath> elevatorTraversalPaths = new LinkedBlockingQueue<>();

    public static final String SPRING_BEAN = "com.sample.coding.exercises.elevator.operation.controller.ElevatorOperationController";


    @Override
    public void acceptElevatorOperationRequest(ElevatorOperationRequest elevatorOperationRequest) {
        Assert.notNull(elevatorOperationRequest, "elevatorOperationRequest cannot be null");
        elevatorOperationRequests.add(elevatorOperationRequest);
    }

    @Override
    public void run() {

        //
        ElevatorTraversalPath pendingElevatorTraversalPath = null;

        while (true) {
            try {
                ElevatorOperationRequest elevatorOperationRequest = elevatorOperationRequests.take();
                ElevatorTraversalPath newElevatorTraversalPath = iElevatorTraversalPathService.computeElevatorTraversalPath(elevator, elevatorOperationRequest);

                // Add this new TravelPath to controller model for managing these.
                //elevatorTraversalPaths.add(elevatorTraversalPath);


                System.out.println("ElevatorOperationRequest:=> " + elevatorOperationRequest + "  Computed elevatorTraversalPath = " + newElevatorTraversalPath);
//                iTraversalOperation.executeElevatorTraversalToDestinationFloor(elevator, newElevatorTraversalPath);
//
//                // Check to see if there are no traversal paths left, this would indicate the request has been fully serviced
//                if(elevatorTraversalPath.getFloorTraversalPath().size() > 0) {
//
//                }
//
//                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    void initElevatorController() {
        System.out.println("Elevator operations initialized and ready for service on Elevator:=> " + elevator);
        listeningExecutorService.submit(this);
    }


}