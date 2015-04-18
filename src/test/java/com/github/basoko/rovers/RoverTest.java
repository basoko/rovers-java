package com.github.basoko.rovers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link Rover}.
 */
public class RoverTest {

    /**
     * Test that a rover executes correctly the spin right command.
     */
    @Test
    public void testExecuteSpinRightCommand() {
        Rover rover = new Rover(new Plateau(new Point(5, 5)), new Point(1, 3), Orientation.NORTH);

        rover.execute(Command.SPIN_RIGHT);
        assertEquals(Orientation.EAST, rover.getOrientation());

        rover.execute(Command.SPIN_RIGHT);
        assertEquals(Orientation.SOUTH, rover.getOrientation());

        rover.execute(Command.SPIN_RIGHT);
        assertEquals(Orientation.WEST, rover.getOrientation());

        rover.execute(Command.SPIN_RIGHT);
        assertEquals(Orientation.NORTH, rover.getOrientation());
    }

    /**
     * Test that a rover executes correctly the spin left command.
     */
    @Test
    public void testExecuteSpinLeftCommand() {
        Rover rover = new Rover(new Plateau(new Point(5, 5)), new Point(1, 3), Orientation.NORTH);

        rover.execute(Command.SPIN_LEFT);
        assertEquals(Orientation.WEST, rover.getOrientation());

        rover.execute(Command.SPIN_LEFT);
        assertEquals(Orientation.SOUTH, rover.getOrientation());

        rover.execute(Command.SPIN_LEFT);
        assertEquals(Orientation.EAST, rover.getOrientation());

        rover.execute(Command.SPIN_LEFT);
        assertEquals(Orientation.NORTH, rover.getOrientation());
    }

    /**
     * Test that a rover executes correctly the move command.
     */
    @Test
    public void testExecuteMoveCommand() {
        Plateau plateau = new Plateau(new Point(5, 5));
        Point point = new Point(1, 3);

        Rover rover = new Rover(plateau, point, Orientation.NORTH);
        rover.execute(Command.MOVE);
        assertEquals(new Point(1, 4), rover.getPosition());

        rover = new Rover(plateau, point, Orientation.EAST);
        rover.execute(Command.MOVE);
        assertEquals(new Point(2, 3), rover.getPosition());

        rover = new Rover(plateau, point, Orientation.SOUTH);
        rover.execute(Command.MOVE);
        assertEquals(new Point(1, 2), rover.getPosition());

        rover = new Rover(plateau, point, Orientation.WEST);
        rover.execute(Command.MOVE);
        assertEquals(new Point(0, 3), rover.getPosition());
    }

    /**
     * Test that a rover renders correctly its position.
     */
    @Test
    public void testShowPosition() {
        Rover rover = new Rover(new Plateau(new Point(5, 5)), new Point(1, 3), Orientation.NORTH);
        assertEquals("1 3 N", rover.showPosition());
    }

    /**
     * Test the equality of two rovers.
     */
    @Test
    public void isEqual() {
        Plateau plateau = new Plateau(new Point(5, 5));
        Point point = new Point(1, 3);

        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(plateau, point, Orientation.NORTH);

        assertTrue(rover1.equals(rover2));
    }

    /**
     * Test the inequality of two rovers with different plateaus.
     */
    @Test
    public void isNotEqualWithDifferentPlateau() {
        Plateau plateau = new Plateau(new Point(5, 5));
        Point point = new Point(1, 3);

        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(new Plateau(new Point(3, 3)), point, Orientation.NORTH);
        assertFalse(rover1.equals(rover2));
    }

    /**
     * Test the inequality of two rovers with different possitions.
     */
    @Test
    public void isNotEqualWithDifferentPositions() {
        Plateau plateau = new Plateau(new Point(5, 5));
        Point point = new Point(1, 3);

        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(plateau, new Point(1, 1), Orientation.NORTH);
        assertFalse(rover1.equals(rover2));
    }

    /**
     * Test the inequality of two rovers with different orientations.
     */
    @Test
    public void isNotEqualWithDifferentOrientation() {
        Plateau plateau = new Plateau(new Point(5, 5));
        Point point = new Point(1, 3);

        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(plateau, point, Orientation.SOUTH);
        assertFalse(rover1.equals(rover2));
    }
}
