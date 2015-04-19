package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Move {@link Command command} of {@link Rover rover}.
 */
public class MoveCommand extends RoverCommand {

    /**
     * Constructs a move {@link Command command}.
     * @param rover The {@link Rover rover} instance.
     */
    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        this.rover.move();
    }

    @Override
    public boolean canExecute() {
        return rover.canMove();
    }
}