package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Orientation;
import com.github.basoko.rovers.Plateau;
import com.github.basoko.rovers.Point;
import com.github.basoko.rovers.Rover;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link CommandFactory}.
 */
public class CommandFactoryTest {
    private Rover rover;

    @Before
    public void before() {
        Plateau plateau = new Plateau(new Point(5, 5));
        rover = new Rover(plateau, new Point(1, 3), Orientation.NORTH);
    }

    @After
    public void after() {
        this.rover  = null;
    }

    /**
     * Test build spin right command from letter 'R'.
     */
    @Test
    public void testBuildSpinRightCommand() {
        Command command = CommandFactory.newInstance(rover, 'R');

        assertTrue(command instanceof SpinRightCommand);
    }

    /**
     * Test build spin left command from letter 'L'.
     */
    @Test
    public void testBuildSpinLeftCommand() {
        Command command = CommandFactory.newInstance(rover, 'L');

        assertTrue(command instanceof SpinLeftCommand);
    }

    /**
     * Test build spin left command from letter 'M'.
     */
    @Test
    public void testBuildMoveCommand() {
        Command command = CommandFactory.newInstance(rover, 'M');

        assertTrue(command instanceof MoveCommand);
    }

    /**
     * Test that verifies that an invalid letter should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCommand() {
        Command command = CommandFactory.newInstance(rover, 'X');
    }
}
