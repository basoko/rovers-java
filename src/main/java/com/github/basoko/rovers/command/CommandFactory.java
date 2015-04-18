package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;

/**
 * Command factory for getting the respective rover command from the letter.
 *
 */
public class CommandFactory {

    /**
     * Constructs a new command instance to be executed by the rover.
     * @param rover The rover instance of the command.
     * @param letter The letter character that represents the command of the rover.
     * @return
     */
    public static final Command newInstance(Rover rover, char letter) {
        switch (letter) {
            case 'R':
                return new SpinRightCommand(rover);
            case 'L':
                return new SpinLeftCommand(rover);
            case 'M':
                return new MoveCommand(rover);
            default:
                throw new IllegalArgumentException();
        }
    }
}
