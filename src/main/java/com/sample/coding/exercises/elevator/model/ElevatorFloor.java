package com.sample.coding.exercises.elevator.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manyce400
 */
public class ElevatorFloor {



    private final int floor;

    private boolean isCurrentFloor;


    public ElevatorFloor(int floor) {
        this.floor = floor;
    }

    public boolean isCurrentFloor() {
        return isCurrentFloor;
    }

    public void setCurrentFloor(boolean currentFloor) {
        isCurrentFloor = currentFloor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ElevatorFloor that = (ElevatorFloor) o;

        return new EqualsBuilder()
                .append(floor, that.floor)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(floor)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("floor", floor)
                .append("isCurrentFloor", isCurrentFloor)
                .toString();
    }
}
