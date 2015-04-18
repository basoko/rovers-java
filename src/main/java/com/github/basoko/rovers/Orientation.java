package com.github.basoko.rovers;

/**
 * Enumeration of the the cardinal compass points.
 */
public enum Orientation {
    /**
     * The North cardinal point.
     */
    NORTH('N'),

    /**
     * The South cardinal point.
     */
    SOUTH('S'),

    /**
     * The East cardinal point.
     */
    EAST('E'),

    /**
     * The West cardinal point.
     */
    WEST('W');

    private final char initial;

    Orientation(char initial) {
        this.initial = initial;
    }

    /**
     * Get a cardinal orientation from its initial.
     * The valid initials are N, S, E and W for North, South, East and West respectively.
     *
     * @param initial The initial character that represents the cardinal orientation.
     * @return the orientation of the initial.
     */
    public static final Orientation fromInitial(char initial) {
        for (Orientation orientation : values()) {
            if (orientation.initial == initial) {
                return orientation;
            }
        }
        throw new IllegalArgumentException("This initial: " + initial + " doesn't represents a valid cardinal orientation!");
    }

    @Override
    public String toString() {
        return String.valueOf(this.initial);
    }
}
