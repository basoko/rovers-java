package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Spin left {@link Command command} of {@link Rover rover}.
 */
public class SpinLeftCommand extends RoverCommand {

    /**
     * Constructs a spin left {@link Command command}.
     * @param rover The {@link Rover rover} instance.
     */
    public SpinLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        this.rover.spinLeft();
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
