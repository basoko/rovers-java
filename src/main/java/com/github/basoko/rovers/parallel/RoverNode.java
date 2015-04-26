package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * A rover node representation.
 */
public class RoverNode {
    private final Rover rover;

    private final List<Command> commands;

    private final int current;

    private final RoverNode prev;

    /**
     * Constructs a new rover node from the initial rover and the list of commands to be executed.
     * @param rover The initial rover node.
     * @param commands The lis of commands to be executed.
     */
    public RoverNode(Rover rover, List<Command> commands) {
        this(rover, null, commands, 0);
    }

    private RoverNode(Rover rover, RoverNode prev, List<Command> commands, int i) {
        this.rover = rover;
        this.commands = commands;
        this.current = i;
        this.prev = prev;
    }

    /**
     * Return true if it commands to execute.
     * @return true if there is a command to execute.
     */
    public boolean hasNext() {
        return this.current < commands.size();
    }

    /**
     * Get the next rover node as a result of the execution of the next command.
     * @return the rover node resulted from the last command executed.
     */
    public RoverNode next() {
        Rover nextRover = rover.execute(commands.get(this.current));

        RoverNode next = new RoverNode(nextRover, this, commands, this.current+1);
        return next;
    }

    /**
     * Get the current rover of the node.
     * @return the current rover.
     */
    public Rover getRover() {
        return this.rover;
    }

    /**
     * Return true if the rover has changed its positions.
     * @return true if the rover has changed its position.
     */
    public boolean hasMoved() {
        if(prev == null) return false;

        return !this.rover.getPosition().equals(prev.getRover().getPosition());
    }
}
