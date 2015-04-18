package com.github.basoko.rovers;

/**
 * Enumeration of rover commands.
 */
public enum Command {

    /**
     * Spin rover 90 degrees to the right.
     */
    SPIN_RIGHT('R'),

    /**
     * Spin rover 90 degrees to the left.
     */
    SPIN_LEFT('L'),

    /**
     * Move rover forward.
     */
    MOVE('M');

    private final char letter;

    Command(char letter) {
        this.letter = letter;
    }

    /**
     * Get a command of the rover from the letter character.
     * The valid letters are R, L, M for spin right, spin left and move respectively.
     * @param letter The letter character that represents the command of the rover.
     * @return the command of the rover from the letter character.
     */
    public static final Command fromLetter(char letter) {
        for(Command command : values()) {
            if(command.letter == letter) {
                return command;
            }
        }
        throw new IllegalArgumentException("This letter: " + letter + " doesn't represents a valid command of the rover!");
    }
}
