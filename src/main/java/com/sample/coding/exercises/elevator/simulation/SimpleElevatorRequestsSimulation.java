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
        for(int i = 0; i < 1; i++) {
            try {
                // Sleep for a few before submitting next request
                //TimeUnit.SECONDS.sleep(4);
                System.out.println("Submitting simulate request");
                ElevatorOperationRequest elevatorOperationRequest = ElevatorOperationRequest.newInstance(2, 10);
                iElevatorOperationController.acceptElevatorOperationRequest(elevatorOperationRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    void startSimulation() {
        System.out.println("Starting Elevator simulation....");


        System.out.println("Submitting simulate request");
        ElevatorOperationRequest elevatorOperationRequest = ElevatorOperationRequest.newInstance(2, 10);
        iElevatorOperationController.acceptElevatorOperationRequest(elevatorOperationRequest);


//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        elevatorOperationRequest = ElevatorOperationRequest.newInstance(4, 8);
//        iElevatorOperationController.acceptElevatorOperationRequest(elevatorOperationRequest);

        //run();
        //listeningExecutorService.submit(this);
    }
}
