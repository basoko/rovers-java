package com.github.basoko.rovers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link Plateau}.
 */
public class PlateauTest {

    /**
     * Test that a creating a plateau with a negative x-axis should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPlateauXAxisNegative() {
        Plateau plateau = new Plateau(new Point(-5, 5));
    }

    /**
     * Test that a creating a plateau with a negative y-axis should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPlateauYAxisNegative() {
        Plateau plateau = new Plateau(new Point(5, -5));
    }

    /**
     * Test that a creating a plateau with a negative x-axis and y-axis should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPlateauBothAxisNegative() {
        Plateau plateau = new Plateau(new Point(-5, -5));
    }

    /**
     * Test that a point given is a valid point of the plateau.
     */
    @Test
    public void testIsValid() {
        Plateau plateau = new Plateau(new Point(5, 5));

        assertTrue(plateau.isValid(new Point(1, 3)));
        assertTrue(plateau.isValid(new Point(0, 0)));
        assertTrue(plateau.isValid(new Point(5, 5)));

        assertFalse(plateau.isValid(new Point(-1, 3)));
        assertFalse(plateau.isValid(new Point(1, -3)));
        assertFalse(plateau.isValid(new Point(-1, -3)));
        assertFalse(plateau.isValid(new Point(6, 2)));
    }

    /**
     * Test the equality of two points.
     */
    @Test
    public void isEqual() {
        Plateau plateau1 = new Plateau(new Point(5, 5));
        Plateau plateau2 = new Plateau(new Point(5, 5));

        assertTrue(plateau1.equals(plateau2));
    }

    /**
     * Test the inequality with null.
     */
    @Test
    public void isNotEqualWithNull() {
        Plateau plateau1 = new Plateau(new Point(5, 5));

        assertFalse(plateau1.equals(null));
    }

    /**
     * Test the inequality of two points.
     */
    @Test
    public void isNotEqual() {
        Plateau plateau1 = new Plateau(new Point(5, 5));
        Plateau plateau2 = new Plateau(new Point(3, 5));

        assertFalse(plateau1.equals(plateau2));
    }

    /**
     * Test the hashCode of two equals plateaus is the same.
     */
    @Test
    public void isHashCodeEquals() {
        Plateau plateau1 = new Plateau(new Point(5, 5));
        Plateau plateau2 = new Plateau(new Point(5, 5));

        assertEquals(plateau1.hashCode(), plateau2.hashCode());
    }
}
