package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Spin right {@link Command command} of {@link Rover rover}.
 */
public class SpinRightCommand implements Command {

    @Override
    public Rover execute(Rover rover) {
        return rover.spinRight();
    }
}
