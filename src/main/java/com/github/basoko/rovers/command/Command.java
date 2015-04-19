package com.github.basoko.rovers.command;

/**
 * Represents a rover command method.
 */
public interface Command {

    /**
     * Execute a command on the rover.
     */
    public void execute();

    /**
     * Indicate if the command can be executed.
     * @return true if the command can be executed otherwise false.
     */
    public boolean canExecute();
}
