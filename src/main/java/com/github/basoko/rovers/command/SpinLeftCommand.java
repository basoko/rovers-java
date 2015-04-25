package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Spin left {@link Command command} of {@link Rover rover}.
 */
public class SpinLeftCommand implements Command {

    @Override
    public Rover execute(Rover rover) {
        return rover.spinLeft();
    }
}
