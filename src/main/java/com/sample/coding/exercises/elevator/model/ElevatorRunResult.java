package com.sample.coding.exercises.elevator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;
import java.util.LinkedList;

/**
 * @author manyce400
 */
public class ElevatorRunResult {


    @Min(value = 1, message = "Total number of floors elevator has traveled cannot be lower than 1")
    private int totalFloorsTraveled;

    // Contains in order all the floors that were traversed as a result of this elevator run.
    private LinkedList<Integer> floorTraversalPath;

    public ElevatorRunResult(int totalFloorsTraveled, LinkedList<Integer> floorTraversalPath) {
        this.totalFloorsTraveled = totalFloorsTraveled;
        this.floorTraversalPath = floorTraversalPath;
    }

    public int getTotalFloorsTraveled() {
        return totalFloorsTraveled;
    }

    public LinkedList<Integer> getFloorTraversalPath() {
        return floorTraversalPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("totalFloorsTraveled", totalFloorsTraveled)
                .append("floorTraversalPath", floorTraversalPath)
                .toString();
    }
}
