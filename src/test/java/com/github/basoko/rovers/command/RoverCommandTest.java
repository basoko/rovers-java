package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Orientation;
import com.github.basoko.rovers.Plateau;
import com.github.basoko.rovers.Point;
import com.github.basoko.rovers.Rover;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link RoverCommand}.
 */
public class RoverCommandTest {
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
     * Test the equality of two whatever rover commands.
     */
    @Test
    public void isEqual() {
        Plateau plateau = new Plateau(new Point(5, 5));
        Rover rover = new Rover(plateau, new Point(1, 3), Orientation.NORTH);

        SpinLeftCommand command = new SpinLeftCommand(this.rover);
        SpinLeftCommand command1 = new SpinLeftCommand(rover);
        assertTrue(command.equals(command1));
    }

    /**
     * Test the inequality with null.
     */
    @Test
    public void isNotEqualWithNull() {
        SpinLeftCommand command1 = new SpinLeftCommand(this.rover);
        assertFalse(command1.equals(null));
    }

    /**
     * Test the inequality of two whatever rover commands.
     */
    @Test
    public void isNotEqual() {
        Plateau plateau = new Plateau(new Point(5, 5));
        Rover rover2 = new Rover(plateau, new Point(3, 3), Orientation.NORTH);

        SpinLeftCommand command1 = new SpinLeftCommand(this.rover);
        SpinLeftCommand command2 = new SpinLeftCommand(rover2);
        assertFalse(command1.equals(command2));
    }
}
