package com.github.basoko.rovers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test suite of {@link Orientation}.
 */
public class OrientationTest {

    /**
     * Test that verifies that valid initial character should returns its cardinal point.
     */
    @Test
    public void testValidInitial() {
        assertEquals(Orientation.NORTH, Orientation.fromInitial('N'));
        assertEquals(Orientation.SOUTH, Orientation.fromInitial('S'));
        assertEquals(Orientation.EAST, Orientation.fromInitial('E'));
        assertEquals(Orientation.WEST, Orientation.fromInitial('W'));
    }

    /**
     * Test that verifies that invalid initial character should throws an {@link IllegalArgumentException illegal argument exception}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInitial() {
        Orientation.fromInitial('X');
    }
}
