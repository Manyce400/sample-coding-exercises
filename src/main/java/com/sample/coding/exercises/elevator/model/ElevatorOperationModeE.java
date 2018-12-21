package com.sample.coding.exercises.elevator.model;

/**
 * Defines the different elevator operation mode supported by our elevator model.
 *
 * @author manyce400
 */
public enum ElevatorOperationModeE {


    // Elevator will run as efficient as possible by optimizing pickups and drop offs for as many people as possible
    EfficientMode,

    // Elevator will run inefficiently servicing 1 request at a time no matter how many pending requests are present for servicing
    InEfficientMode
    ;

}
