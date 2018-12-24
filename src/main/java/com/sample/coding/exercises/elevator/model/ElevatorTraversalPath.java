package com.sample.coding.exercises.elevator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.LinkedList;

/**
 * The concept of an ElevatorTraversalPath dictates where the full path that the elevator has to travel on to fulfil
 * and execute Elevator operation requests.  Note that this path can change realtime based on continuous incoming requests
 *
 * @author manyce400
 */
public class ElevatorTraversalPath {


    @Min(value = 1, message = "Total number of floors elevator has traveled cannot be lower than 1")
    private int totalFloorsTraveled = 0;

    // Contains in order all the floors that will be traversed on this current path elevator on.
    private LinkedList<Integer> floorTraversalPath;

    // If a secondary traversal path is combined with this, secondary details will be tracked in below list
    private LinkedList<LinkedList<Integer>> chainedFloorTraversalPath = new LinkedList<>();

    public ElevatorTraversalPath(LinkedList<Integer> floorTraversalPath) {
        this.floorTraversalPath = floorTraversalPath;
    }

    public int getTotalFloorsTraveled() {
        return totalFloorsTraveled;
    }

    public void setTotalFloorsTraveled(int totalFloorsTraveled) {
        this.totalFloorsTraveled = totalFloorsTraveled;
    }

    public LinkedList<Integer> getFloorTraversalPath() {
        return floorTraversalPath;
    }

    public boolean containsAdditionalFloorTraversalPaths() {
        return chainedFloorTraversalPath.size() > 0;
    }

    public boolean isAdditionalFloorTraversalPathFloorStop(Integer floor) {
        boolean stopOnFloor = false;

        for(LinkedList<Integer> traversalPath : chainedFloorTraversalPath) {
            if(traversalPath.contains(floor)) {
                stopOnFloor = true;
                break;
            }
        }

        return stopOnFloor;
    }

    public void mergeFloorTraversalPath(LinkedList<Integer> floorTraversalPath) {
        Assert.notNull(floorTraversalPath, "floorTraversalPath cannot be null");
        Assert.isTrue(floorTraversalPath.size() >0, "floorTraversalPath cannot be empty");
        chainedFloorTraversalPath.add(floorTraversalPath);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("totalFloorsTraveled", totalFloorsTraveled)
                .append("floorTraversalPath", floorTraversalPath)
                .append("chainedFloorTraversalPath", chainedFloorTraversalPath)
                .toString();
    }


    public static ElevatorTraversalPath newInstance(Integer ... floors) {
        LinkedList<Integer> traversalFloors = new LinkedList<>();
        Collections.addAll(traversalFloors, floors);
        return new ElevatorTraversalPath(traversalFloors);
    }
}
