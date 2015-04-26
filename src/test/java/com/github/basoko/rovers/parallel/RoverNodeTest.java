package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.Orientation;
import com.github.basoko.rovers.Plateau;
import com.github.basoko.rovers.Point;
import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.command.Command;
import com.github.basoko.rovers.command.MoveCommand;
import com.github.basoko.rovers.command.SpinLeftCommand;
import com.github.basoko.rovers.command.SpinRightCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link RoverNode}.
 */
public class RoverNodeTest {
    private Rover rover;
    private List<Command> commands;

    @Before
    public void before() {
        Plateau plateau = new Plateau(new Point(5, 5));
        this.rover = new Rover(plateau, new Point(0, 0), Orientation.NORTH);

        this.commands = new ArrayList<>();
        commands.add(new MoveCommand());
        commands.add(new SpinRightCommand());
        commands.add(new MoveCommand());
        commands.add(new SpinLeftCommand());
    }

    @After
    public void after() {
        this.rover = null;
    }

    /**
     * Test iterate over rover node.
     */
    @Test
    public void iterateOverRoverNode() {
        RoverNode node = new RoverNode(rover, commands);

        for(int i = 0; i < commands.size(); i++) {
            assertTrue(node.hasNext());
            node = node.next();
        }

        assertFalse(node.hasNext());
    }

    /**
     * Test hasNext return true if there are nodes.
     */
    @Test
    public void hasNextIsTrue() {
        RoverNode node = new RoverNode(rover, commands);

        assertTrue(node.hasNext());
    }

    /**
     * Test hasNext return false if there aren't nodes.
     */
    @Test
    public void hasNextIsFalse() {
        RoverNode node = new RoverNode(rover, new ArrayList<Command>());

        assertFalse(node.hasNext());
    }

    /**
     * Test hasMove return true if it has changed the position.
     */
    @Test
    public void hasMovedIsTrue() {
        RoverNode node = new RoverNode(rover, commands);
        node = node.next();

        assertTrue(node.hasMoved());

    }

    /**
     * Test hasMove return false if it hasn't changed the position.
     */
    @Test
    public void hasMovedIsFalse() {
        RoverNode node = new RoverNode(rover, commands);
        node = node.next();
        node = node.next();

        assertFalse(node.hasMoved());

    }
}
