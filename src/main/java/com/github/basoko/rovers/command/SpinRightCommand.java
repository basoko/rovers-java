package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Spin right {@link Command command} of {@link Rover rover}.
 */
public class SpinRightCommand extends RoverCommand {

    /**
     * Constructs a spin right {@link Command command}.
     * @param rover The {@link Rover rover} instance.
     */
    public SpinRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        this.rover.spinRight();
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
