package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Move {@link Command command} of {@link Rover rover}.
 */
public class MoveCommand implements Command {

    @Override
    public Rover execute(Rover rover) {
        return rover.move();
    }
}