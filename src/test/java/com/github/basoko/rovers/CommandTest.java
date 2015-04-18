package com.github.basoko.rovers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test suite of {@link Command}.
 */
public class CommandTest {

    /**
     * Test that verifies that valid letter character should returns its valid rover command.
     */
    @Test
    public void testValidLetter() {
        assertEquals(Command.SPIN_RIGHT, Command.fromLetter('R'));
        assertEquals(Command.SPIN_LEFT, Command.fromLetter('L'));
        assertEquals(Command.MOVE, Command.fromLetter('M'));
    }

    /**
     * Test that verifies that invalid letter character should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLetter() {
        Command.fromLetter('X');
    }
}
