package com.sample.coding.exercises.elevator.config;

import com.sample.coding.exercises.elevator.model.Elevator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ElevatorConfiguration {


    public static final String DEFAL_ELEVATOR_BEAN = "com.sample.coding.exercises.elevator.config.Elevator";

    @Bean(ElevatorConfiguration.DEFAL_ELEVATOR_BEAN)
    public Elevator configureElevator() {
        Elevator elevator = new Elevator(12);
        return elevator;
    }
}
