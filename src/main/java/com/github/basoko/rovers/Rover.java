package com.github.basoko.rovers;

/**
 * The Mars rover.
 */
public class Rover {

    private Plateau plateau;

    private Point position;

    private Orientation orientation;

    /**
     * Constructs a rover with a {@link Point position} in the {@link Plateau plateau}
     * and its {@link Orientation orientation}.
     * @param plateau The {@link Plateau plateau} where the rover is.
     * @param position The rover {@link Point position} in the  {@link Plateau plateau}.
     * @param orientation The rover {@link Orientation orientation}.
     */
    public Rover(Plateau plateau, Point position, Orientation orientation) {
        this.plateau = plateau;
        this.position = position;
        this.orientation = orientation;
    }

    /**
     * Executes a rover {@link Command command}.
     * and its {@link Orientation orientation}.
     * @param command The {@link Command command} to be executed in the rover.
     */
    public void execute(Command command) {
        switch (command) {
            case SPIN_RIGHT:
                spinRight();
                break;
            case SPIN_LEFT:
                spinLeft();
                break;
            case MOVE:
                move();
                break;
        }
    }

    /**
     * Spin the rover 90 degrees to the right.
     */
    public void spinRight() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    /**
     * Spin the rover 90 degrees to the left.
     */
    public void spinLeft() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.WEST;
                break;
            case SOUTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.NORTH;
                break;
            case WEST:
                orientation = Orientation.SOUTH;
                break;
        }
    }

    /**
     * Move the rover to a new position depending on its current orientation.
     */
    public void move() {
        Point position = getNewPosition();

        if(!canMoveTo(position)) {
            throw new IllegalStateException("Can't move to the indicated position: " + position);
        }

        this.position = position;
    }

    /**
     * Get a {@link String string} representation of the rover position and orientation. The string format is build
     * firstly by the cartesian points and then by the representation of the cardinal point separated by a space.
     * <p>
     * For example a position in the cartesian point (1, 3) with North orientation will return: 1 3 N
     *
     * @return the current {@link Orientation orientation} of the rover.
     */
    public String showPosition() {
        StringBuilder position = new StringBuilder();

        position.append(this.position.x)
                .append(" ")
                .append(this.position.y)
                .append(" ")
                .append(this.orientation.toString());

        return position.toString();
    }

    /**
     * Get the current {@link Point position} of the rover.
     * @return the current {@link Point position} of the rover.
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Get the current {@link Orientation orientation} of the rover.
     * @return the current {@link Orientation orientation} of the rover.
     */
    public Orientation getOrientation() {
        return this.orientation;
    }

    /**
     * Returns true if the specified object is equal to the rover.
     * Two rovers are equal if they are in the same points of the
     * same plateau and also have the same orientation.
     * @param o The object to be compared.
     * @return true if points are equal otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Rover) {
            Rover other = (Rover) o;
            return this.plateau.equals(other.plateau) &&
                    this.position.equals(other.position) &&
                    this.orientation.equals(other.orientation);
        }

        return false;
    }

    private Point getNewPosition() {
        switch(orientation) {
            case NORTH:
                return new Point(this.position.x, this.position.y+1);
            case SOUTH:
                return new Point(this.position.x, this.position.y-1);
            case EAST:
                return new Point(this.position.x+1, this.position.y);
            case WEST:
                return new Point(this.position.x-1, this.position.y);
        }

        return null;
    }

    private boolean canMoveTo(Point point) {
        return plateau.isValid(point);
    }
}
