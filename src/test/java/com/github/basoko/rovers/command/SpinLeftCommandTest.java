package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Orientation;
import com.github.basoko.rovers.Plateau;
import com.github.basoko.rovers.Point;
import com.github.basoko.rovers.Rover;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test suite of {@link SpinLeftCommand}.
 */
public class SpinLeftCommandTest {
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
     * Test the rover spins left executing the command.
     */
    @Test
    public void testSpinLeft() {
        SpinLeftCommand command = new SpinLeftCommand(rover);

        rover.execute(command);

        assertEquals(new Point(1, 3), rover.getPosition());
        assertEquals(Orientation.WEST, rover.getOrientation());
    }
}
