package com.github.basoko.rovers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link Point}.
 */
public class PointTest {

    /**
     * Test the equality of two points.
     */
    @Test
    public void isEqual() {
        Point point1 = new Point(3, 3);
        Point point2 = new Point(3, 3);

        assertTrue(point1.equals(point2));
    }

    /**
     * Test the inequality with null.
     */
    @Test
    public void isNotEqualWithNull() {
        Point point1 = new Point(3, 3);
        assertFalse(point1.equals(null));
    }

    /**
     * Test the inequality of two points.
     */
    @Test
    public void isNotEqual() {
        Point point1 = new Point(3, 3);
        Point point3 = new Point(1, 2);
        assertFalse(point1.equals(point3));
    }

    /**
     * Test the hashCode of two equals points is the same.
     */
    @Test
    public void isHashCodeEquals() {
        Point point1 = new Point(3, 3);
        Point point2 = new Point(3, 3);

        assertEquals(point1.hashCode(), point2.hashCode());
    }
}
