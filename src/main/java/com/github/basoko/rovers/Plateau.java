package com.github.basoko.rovers;

/**
 * The Mars plateau.
 */
public class Plateau {

    private final Point bottomLeft;

    private final Point upperRight;

    /**
     * Constructs a plateau using {@link Point point} as its upper-right limit.
     * The plateau bottom-left limit {@link Point point} is by default 0, 0.
     * @param point The upper-right point of the plateau.
     */
    public Plateau(Point point) {
        if(point.x < 0 || point.y < 0) {
            throw new IllegalArgumentException("The point has to be a valid point of the first cartesian plane.");
        }

        this.bottomLeft = new Point(0, 0);
        this.upperRight = point;
    }

    /**
     * Check if the given point is valid point of the plateau.
     * @param point The point to check if it's a valid point of the plateau.
     * @return true if is a point is inside the plateau otherwise false.
     */
    public boolean isValid(Point point) {
        return point.x >= bottomLeft.x && point.x <= upperRight.x
                && point.y >= bottomLeft.y && point.y <= upperRight.y;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + bottomLeft.hashCode();
        result = 31 * result + upperRight.hashCode();

        return result;
    }

    /**
     * Returns true if the specified object is equal to the plateau.
     * Two plateaus are equal if they have the same coordinates.
     * @param o The object to be compared.
     * @return true if plateaus are equal otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;

        if(o == null) return false;

        if(!(o instanceof Plateau)) return false;

        Plateau other = (Plateau) o;
        return this.bottomLeft.equals(other.bottomLeft) &&
                this.upperRight.equals(other.upperRight);

    }
}
