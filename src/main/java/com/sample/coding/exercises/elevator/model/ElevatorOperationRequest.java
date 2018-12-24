package com.sample.coding.exercises.elevator.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;

/**
 * @author manyce400
 */
public class ElevatorOperationRequest {


    // Floor where a pickup will be executed.
    @Min(value = 1, message = "Lowest floor that our elevator model  supports")
    private int pickupFloor;

    // Floor where drop off needs to be executed.
    @Min(value = 1, message = "Lowest floor that our elevator model  supports")
    private int dropOffFloor;


    public ElevatorOperationRequest(int pickupFloor, int dropOffFloor) {
        this.pickupFloor = pickupFloor;
        this.dropOffFloor = dropOffFloor;
    }

    public int getPickupFloor() {
        return pickupFloor;
    }

    public void setPickupFloor(int pickupFloor) {
        this.pickupFloor = pickupFloor;
    }

    public int getDropOffFloor() {
        return dropOffFloor;
    }

    public void setDropOffFloor(int dropOffFloor) {
        this.dropOffFloor = dropOffFloor;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(pickupFloor)
                .append(dropOffFloor)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        ElevatorOperationRequest other = (ElevatorOperationRequest)obj;
        return new EqualsBuilder()
                .append(pickupFloor, other.pickupFloor)
                .append(dropOffFloor, other.dropOffFloor)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("pickupFloor", pickupFloor)
                .append("dropOffFloor", dropOffFloor)
                .toString();
    }


    public static ElevatorOperationRequest newInstance(int pickupFloor, int dropOffFloor) {
        return new ElevatorOperationRequest(pickupFloor, dropOffFloor);
    }
}
