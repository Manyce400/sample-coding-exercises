package com.sample.coding.exercises.elevator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.stream.IntStream;

/**
 * Domain object representing all the critical properties of an Elevator
 *
 * @author manyce400
 */
public class Elevator {



    private final int totalFloors;

    private int currentFloor;

    //private LinkedList<ElevatorFloor> elevatorFloors = new LinkedList<>();

    private ElevatorFloor[] elevatorFloors;

    // By default this elevator will run in efficient mode.  This can be modified to run in a different mode
    private ElevatorOperationModeE elevatorOperationModeE = ElevatorOperationModeE.EfficientMode;


    public Elevator(int totalFloors) {
        this.totalFloors = totalFloors;
        this.currentFloor = 1;
        initElevatorFloors();
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        elevatorFloors[currentFloor].setCurrentFloor(true);
    }

    public ElevatorOperationModeE getElevatorOperationModeE() {
        return elevatorOperationModeE;
    }

    public void setElevatorOperationModeE(ElevatorOperationModeE elevatorOperationModeE) {
        this.elevatorOperationModeE = elevatorOperationModeE;
    }

    // Initialize this elevator with the total number of floors that have been created in constructor
    private void initElevatorFloors() {
        elevatorFloors = new  ElevatorFloor[totalFloors];

        IntStream.rangeClosed(1, totalFloors).forEach(
                floor -> {
                    ElevatorFloor elevatorFloor = new ElevatorFloor(floor);
                    int arrayIndex = floor -1;
                    elevatorFloors[arrayIndex] = elevatorFloor;
                }
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("totalFloors", totalFloors)
                .append("currentFloor", currentFloor)
                .append("elevatorFloors", elevatorFloors)
                .append("elevatorOperationModeE", elevatorOperationModeE)
                .toString();
    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator(12);
    }

}