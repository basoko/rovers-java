package com.github.basoko.rovers.command;

/**
 * Command factory for getting the respective rover command from the letter.
 *
 */
public class CommandFactory {

    /**
     * Constructs a new command instance to be executed by the rover.
     * @param letter The letter character that represents the command of the rover.
     * @return the command instance.
     */
    public static final Command newInstance(char letter) {
        switch (letter) {
            case 'R':
                return new SpinRightCommand();
            case 'L':
                return new SpinLeftCommand();
            case 'M':
                return new MoveCommand();
            default:
                throw new IllegalArgumentException();
        }
    }
}
