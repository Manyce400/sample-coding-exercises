package com.sample.coding.exercises.elevator.simulation;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.sample.coding.exercises.elevator.config.ThreadPoolConfiguration;
import com.sample.coding.exercises.elevator.model.ElevatorOperationRequest;
import com.sample.coding.exercises.elevator.operation.controller.ElevatorOperationController;
import com.sample.coding.exercises.elevator.operation.controller.IElevatorOperationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author manyce400
 */
@Component
class SimpleElevatorRequestsSimulation implements Runnable {




    @Autowired
    @Qualifier(ThreadPoolConfiguration.ELEVATOR_OPS_THREAD_POOL)
    private ListeningExecutorService listeningExecutorService;

    @Autowired
    @Qualifier(ElevatorOperationController.SPRING_BEAN)
    private IElevatorOperationController iElevatorOperationController;


    @Override
    public void run() {
        while (true) {
            try {
                // Sleep for a few before submitting next request
                TimeUnit.SECONDS.sleep(4);
                ElevatorOperationRequest elevatorOperationRequest = ElevatorOperationRequest.newInstance(2, 10);
                iElevatorOperationController.acceptElevatorOperationRequest(elevatorOperationRequest);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    void startSimulation() {
        System.out.println("Starting Elevator simulation....");
        listeningExecutorService.submit(this);
    }
}
