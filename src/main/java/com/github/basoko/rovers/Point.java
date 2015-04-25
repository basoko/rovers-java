package com.github.basoko.rovers;

/**
 * Coordinates of a point in the cartesian system.
 */
public class Point {

    /**
     * The x axis.
     */
    public int x;

    /**
     * The y axis.
     */
    public int y;

    /**
     * Creates a new point.
     *
     * @param x The x axis of the point.
     * @param y The y axis of the point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns true if the specified object is equal to the point.
     * Two points are equal if they have the same cartesian coordinates.
     * @param o The object to be compared.
     * @return true if points are equal otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;

        if(o == null) return false;

        if(!(o instanceof Point)) return false;

        Point other = (Point) o;
        return other.x == this.x && other.y == this.y;
    }

    /**
     * Get a string representation of the point.
     * The string format will be this { x: value, y: value }.
     * @return the string representation of the point.
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append("{ x: ").append(x).append(", ")
                .append("y: ").append(y)
                .append(" }")
                .toString();
    }
}

