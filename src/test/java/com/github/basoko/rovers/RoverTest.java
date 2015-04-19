package com.github.basoko.rovers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test suite of {@link Rover}.
 */
public class RoverTest {

    private Plateau plateau;
    private Point point;

    @Before
    public void before() {
        this.plateau = new Plateau(new Point(5, 5));
        this.point = new Point(1, 3);
    }

    @After
    public void after() {
        this.plateau = null;
        this.point = null;
    }

    /**
     * Test spin right method of rover.
     */
    @Test
    public void testSpinRight() {
        Rover rover = new Rover(plateau, point, Orientation.NORTH);

        rover.spinRight();
        assertEquals(rover.getOrientation(), Orientation.EAST);

        rover.spinRight();
        assertEquals(rover.getOrientation(), Orientation.SOUTH);

        rover.spinRight();
        assertEquals(rover.getOrientation(), Orientation.WEST);

        rover.spinRight();
        assertEquals(rover.getOrientation(), Orientation.NORTH);
    }

    /**
     * Test spin left method of rover.
     */
    @Test
    public void testSpinLeft() {
        Rover rover = new Rover(plateau, point, Orientation.NORTH);

        rover.spinLeft();
        assertEquals(rover.getOrientation(), Orientation.WEST);

        rover.spinLeft();
        assertEquals(rover.getOrientation(), Orientation.SOUTH);

        rover.spinLeft();
        assertEquals(rover.getOrientation(), Orientation.EAST);

        rover.spinLeft();
        assertEquals(rover.getOrientation(), Orientation.NORTH);
    }

    /**
     * Test move method of rover.
     */
    @Test
    public void testMove() {
        Rover rover = new Rover(plateau, new Point(1, 3), Orientation.NORTH);
        rover.move();
        assertEquals(new Point(1, 4), rover.getPosition());

        rover = new Rover(plateau, new Point(1, 3), Orientation.SOUTH);
        rover.move();
        assertEquals(new Point(1, 2), rover.getPosition());

        rover = new Rover(plateau, new Point(1, 3), Orientation.EAST);
        rover.move();
        assertEquals(new Point(2, 3), rover.getPosition());

        rover = new Rover(plateau, new Point(1, 3), Orientation.WEST);
        rover.move();
        assertEquals(new Point(0, 3), rover.getPosition());
    }

    /**
     * Test move method of rover outside of plateau should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalStateException.class)
    public void testMoveOutsidePlateau() {
        Rover rover = new Rover(plateau, new Point(5, 5), Orientation.NORTH);
        rover.move();
    }

    /**
     * Test that a rover renders correctly its position.
     */
    @Test
    public void testShowPosition() {
        Rover rover = new Rover(plateau, point, Orientation.NORTH);
        assertEquals("1 3 N", rover.showPosition());
    }

    /**
     * Test the equality of two rovers.
     */
    @Test
    public void isEqual() {
        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(plateau, point, Orientation.NORTH);

        assertTrue(rover1.equals(rover2));
    }

    /**
     * Test the inequality of two rovers with different plateaus.
     */
    @Test
    public void isNotEqualWithDifferentPlateau() {
        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(new Plateau(new Point(3, 3)), point, Orientation.NORTH);
        assertFalse(rover1.equals(rover2));
    }

    /**
     * Test the inequality of two rovers with different possitions.
     */
    @Test
    public void isNotEqualWithDifferentPositions() {
        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(plateau, new Point(1, 1), Orientation.NORTH);
        assertFalse(rover1.equals(rover2));
    }

    /**
     * Test the inequality of two rovers with different orientations.
     */
    @Test
    public void isNotEqualWithDifferentOrientation() {
        Rover rover1 = new Rover(plateau, point, Orientation.NORTH);
        Rover rover2 =  new Rover(plateau, point, Orientation.SOUTH);
        assertFalse(rover1.equals(rover2));
    }
}
