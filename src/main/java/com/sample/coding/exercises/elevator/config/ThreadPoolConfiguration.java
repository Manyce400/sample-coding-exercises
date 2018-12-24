package com.sample.coding.exercises.elevator.config;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Component
public class ThreadPoolConfiguration {

    public static final String ELEVATOR_OPS_THREAD_POOL = "com.sample.coding.exercises.elevator.config.ElevatorOpsThreadPool";

    @Bean(name = ELEVATOR_OPS_THREAD_POOL)
    ListeningExecutorService configureElevatorOperationsThreadPool() {
        return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }

}
