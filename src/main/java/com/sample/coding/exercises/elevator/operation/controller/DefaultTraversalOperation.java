package com.sample.coding.exercises.elevator.operation.controller;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.sample.coding.exercises.elevator.config.ElevatorConfiguration;
import com.sample.coding.exercises.elevator.config.ThreadPoolConfiguration;
import com.sample.coding.exercises.elevator.model.Elevator;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPath;
import com.sample.coding.exercises.elevator.model.ElevatorTraversalPathException;
import com.sample.coding.exercises.elevator.operation.traversal.ElevatorTraversalPathService;
import com.sample.coding.exercises.elevator.operation.traversal.IElevatorTraversalPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is the main component responsible for moving the elevator up or down to fulfill all Elevator traversal requests.
 *
 *
 * @author manyce400
 */
@Service(DefaultTraversalOperation.SPRING_BEAN)
public class DefaultTraversalOperation implements ITraversalOperation {




    @Autowired
    @Qualifier(ElevatorConfiguration.DEFAL_ELEVATOR_BEAN)
    private Elevator elevator ;


    @Autowired
    @Qualifier(ElevatorTraversalPathService.SPRING_BEAN)
    private IElevatorTraversalPathService iElevatorTraversalPathService;

    @Autowired
    @Qualifier(ThreadPoolConfiguration.ELEVATOR_OPS_THREAD_POOL)
    private ListeningExecutorService listeningExecutorService;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private LinkedList<ElevatorTraversalPath> elevatorTraversalPaths = new LinkedList<>();

    private static final int MOVE_OPERATION_DELAY = 2;

    public static final String SPRING_BEAN = "com.sample.coding.exercises.elevator.operation.controller.DefaultTraversalOperation";


    @Override
    public void executeContinuousElevatorTraversal(Elevator elevator) {

        System.out.println("Size of traversal paths: "+elevatorTraversalPaths.size());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (elevatorTraversalPaths.size() > 0) {
            ElevatorTraversalPath primaryTraversalPath = elevatorTraversalPaths.getFirst();
            System.out.println("Beginning processing of primaryTraversalPath = " + primaryTraversalPath);

            if(primaryTraversalPath.isTraversalComplete()) {
                elevatorTraversalPaths.removeFirst();
            } else {
                elevatorTraversalPaths.removeFirst();

                // Combine primary with all similar elevator traversal paths.
                combineWithEligibleElevatorTraversalPaths(primaryTraversalPath);

                while (!primaryTraversalPath.isTraversalComplete()) {
                    // Execute traversal to destination floor
                    executeElevatorTraversalToDestinationFloor(elevator, primaryTraversalPath);
                }
            }
        }

    }

    @Override
    public void executeElevatorTraversalToDestinationFloor(Elevator elevator, ElevatorTraversalPath elevatorTraversalPath) {
        Assert.notNull(elevator, "elevator cannot be null");
        Assert.notNull(elevatorTraversalPath, "elevatorTraversalPath cannot be null");
        Assert.isTrue(elevatorTraversalPath.getFloorTraversalPath().size() > 0, "");

        try {
            lock.writeLock().lock();

            int pickUpFloor = elevatorTraversalPath.getFloorTraversalPath().getFirst();
            int dropOffFloor = elevatorTraversalPath.getFloorTraversalPath().getLast();

            System.out.println("Elevator current floor:> " + elevator.getCurrentFloor() +  " Executing traversal from pickupFloor:=> "+ pickUpFloor + " to dropOffFloor:=> " + dropOffFloor);
            System.out.println();

            // Execute traversal to get to pickup destination
            if(elevator.getCurrentFloor() < pickUpFloor) {
                System.out.println("Running elevator move up to pickup floor:> "+pickUpFloor);
                while (elevator.getCurrentFloor() < pickUpFloor) {
                    moveElevatorUpToTargetFloor(pickUpFloor, elevator, elevatorTraversalPath);
                }
            } else if(elevator.getCurrentFloor() > pickUpFloor) {
                System.out.println("Running elevator move up to pickup floor:> "+pickUpFloor);
                while (elevator.getCurrentFloor() > pickUpFloor) {
                    moveElevatorDownToTargetFloor(pickUpFloor, elevator, elevatorTraversalPath);
                }
            }

            // Complete traversal to destination/drop off floor
            if(elevator.getCurrentFloor() < dropOffFloor) {
                System.out.println("Running elevator move up to drop off floor:> "+dropOffFloor);
                while (elevator.getCurrentFloor() < dropOffFloor) {
                    moveElevatorUpToTargetFloor(dropOffFloor, elevator, elevatorTraversalPath);
                }
            } else if(elevator.getCurrentFloor() > dropOffFloor) {
                System.out.println("Running elevator move down to drop floor:> "+dropOffFloor);
                while (elevator.getCurrentFloor() > dropOffFloor) {
                    moveElevatorDownToTargetFloor(dropOffFloor, elevator, elevatorTraversalPath);
                }
            }


            // Complete all primary and combined floor traversals
            elevatorTraversalPath.completePrimaryFloorTraversalPath();
            elevatorTraversalPath.completeAllCombinedPathTraversal();


            System.out.println("All floors that were stopped on as part of this move operation:>  "+elevatorTraversalPath.getStopFloorsOnTraversalPath());
        } catch (InterruptedException e) {
            System.out.println("Exception occurred while elevator was moving to target floor: " + e);
        } finally {
            lock.writeLock().unlock();
        }
    }


    void combineWithEligibleElevatorTraversalPaths(ElevatorTraversalPath primaryTraversalPath) {
        List<ElevatorTraversalPath> combinedElevatorTraversalPaths = new ArrayList<>();

        elevatorTraversalPaths.forEach(secondaryElevatorTraversalPath -> {
            boolean canCombineTraversalPath = iElevatorTraversalPathService.canCombineTraversalPath(elevator, primaryTraversalPath, secondaryElevatorTraversalPath);

            if (canCombineTraversalPath) {
                try {
                    iElevatorTraversalPathService.combineElevatorTraversalPath(elevator, primaryTraversalPath, secondaryElevatorTraversalPath);
                    combinedElevatorTraversalPaths.add(secondaryElevatorTraversalPath);
                } catch (ElevatorTraversalPathException e) {
                    e.printStackTrace();
                }
            }
        });

        if(combinedElevatorTraversalPaths.size() > 0) {
            elevatorTraversalPaths.removeAll(combinedElevatorTraversalPaths);
        }
    }


    void moveElevatorUpToTargetFloor(int targetFloor, Elevator elevator, ElevatorTraversalPath elevatorTraversalPath) throws InterruptedException {
        int currentFloor = elevator.getCurrentFloor();

        for(int i = currentFloor; i<= targetFloor; i++) {
            System.out.println("Executing move_up operation, currently on floor:=> "+i);

            // Update Elevator details to reflect changes
            elevator.setCurrentFloor(i);

            // Sleep to simulate time taken by physical movement of elevator to a floor
            TimeUnit.SECONDS.sleep(MOVE_OPERATION_DELAY);

            // Check to see if a stop needs to be made on this floor due to a combined traversal path
            boolean stopOnFloor = elevatorTraversalPath.isAdditionalFloorTraversalPathFloorStop(i);
            if(stopOnFloor) {
                // Stop on this floor.  Break out and return control so new traversals can be executed from this point.
                System.out.println("Making a stop on this floor to pickup additional riders traveling along this path");

                // Indicate to our model that this combined floor has now been visited, we wont stop on this floor again.
                elevatorTraversalPath.completeStopOnCombinedPathFloor(i);
                break;
            }
        }

        // At this point move up is completed and elevator has stopped on the current floor
        elevatorTraversalPath.completeStopOnPrimaryFloor(elevator.getCurrentFloor());
        System.out.println("Stopped on floor:> " + elevator.getCurrentFloor()+"\n");

    }

    void moveElevatorDownToTargetFloor(int targetFloor, Elevator elevator, ElevatorTraversalPath elevatorTraversalPath) throws InterruptedException {
        int currentFloor = elevator.getCurrentFloor();

        for(int i = currentFloor; i >= targetFloor; i--) {
            System.out.println("Executing Elevator move down operation, currently on floor:=> "+i);

            // Update Elevator details to reflect changes
            elevator.setCurrentFloor(i);

            // Sleep to simulate time taken by physical movement of elevator to a floor
            TimeUnit.SECONDS.sleep(MOVE_OPERATION_DELAY);

            // Check to see if a stop needs to be made on this floor due to a combined traversal path
            boolean stopOnFloor = elevatorTraversalPath.isAdditionalFloorTraversalPathFloorStop(i);
            if(stopOnFloor) {
                // Stop on this floor.  Break out and return control so new traversals can be executed from this point.
                System.out.println("Making a stop on this floor to pickup additional riders traveling along this path");

                // Indicate to our model that this combined floor has now been visited, we wont stop on this floor again.
                elevatorTraversalPath.completeStopOnCombinedPathFloor(i);
                break;
            }
        }

        // At this point move up is completed and elevator has stopped on the current floor
        elevatorTraversalPath.completeStopOnPrimaryFloor(elevator.getCurrentFloor());
        System.out.println("Stopped on floor:> " + elevator.getCurrentFloor()+"\n");
    }


    public void addElevatorTraversalPath(final ElevatorTraversalPath elevatorTraversalPath) {
        Assert.notNull(elevatorTraversalPath, "elevatorTraversalPath cannot be null");
        elevatorTraversalPaths.add(elevatorTraversalPath);
    }



    @PostConstruct
    public void runOperations() {
        Runnable execOperationsTask = () -> {
            while (true) {
                executeContinuousElevatorTraversal(elevator);
            }
        };

        listeningExecutorService.execute(execOperationsTask);
    }

}
