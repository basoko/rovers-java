package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Represents a rover command method.
 */
public interface Command {

    /**
     * Execute a command on the rover.
     */
    public Rover execute(Rover rover);
}
