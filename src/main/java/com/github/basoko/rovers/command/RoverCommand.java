package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Rover command base class.
 */
public abstract class RoverCommand implements Command {
    protected Rover rover;

    /**
     * Returns true if the specified object is equal to rover {@link Command command}.
     * Two commands are equal if are executed on the same
     *    *      {@link Rover rover}.
     * @param o The object to be compared.
     * @return true if the rover {@link Command command} is executed on the same
     *      {@link Rover rover} otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof RoverCommand) {
            RoverCommand other = (RoverCommand) o;
            return this.rover.equals(other.rover);
        }

        return false;
    }
}
