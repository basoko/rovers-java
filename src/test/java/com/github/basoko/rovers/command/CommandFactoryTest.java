package com.github.basoko.rovers.command;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link CommandFactory}.
 */
public class CommandFactoryTest {

    /**
     * Test build spin right command from letter 'R'.
     */
    @Test
    public void testBuildSpinRightCommand() {
        Command command = CommandFactory.newInstance('R');

        assertTrue(command instanceof SpinRightCommand);
    }

    /**
     * Test build spin left command from letter 'L'.
     */
    @Test
    public void testBuildSpinLeftCommand() {
        Command command = CommandFactory.newInstance('L');

        assertTrue(command instanceof SpinLeftCommand);
    }

    /**
     * Test build spin left command from letter 'M'.
     */
    @Test
    public void testBuildMoveCommand() {
        Command command = CommandFactory.newInstance('M');

        assertTrue(command instanceof MoveCommand);
    }

    /**
     * Test that verifies that an invalid letter should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCommand() {
        Command command = CommandFactory.newInstance('X');
    }
}
