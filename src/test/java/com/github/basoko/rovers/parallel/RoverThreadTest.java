package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.Orientation;
import com.github.basoko.rovers.Plateau;
import com.github.basoko.rovers.Point;
import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.command.Command;
import com.github.basoko.rovers.command.MoveCommand;
import com.github.basoko.rovers.command.SpinLeftCommand;
import com.github.basoko.rovers.command.SpinRightCommand;
import com.github.basoko.rovers.exception.BlockedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite of {@link RoverThread}.
 */
public class RoverThreadTest {
    private RoverThread roverThread1;

    private RoverThread roverThread2;

    @Before
    public void before() {
        Plateau plateau = new Plateau(new Point(5, 5));

        Map<Point, Lock> locks = new HashMap<>();
        for(Point point : plateau.getAllPoints()) {
            locks.put(point, new ReentrantLock());
        }

        Rover rover1 = new Rover(plateau, new Point(0, 0), Orientation.NORTH);

        ArrayList<Command> commands1 = new ArrayList<>();
        commands1.add(new MoveCommand());
        commands1.add(new SpinRightCommand());
        commands1.add(new MoveCommand());
        commands1.add(new SpinLeftCommand());

        RoverNode roverNode1 = new RoverNode(rover1, commands1);

        Rover rover2 = new Rover(plateau, new Point(0, 1), Orientation.SOUTH);

        ArrayList<Command> commands2 = new ArrayList<>();
        commands2.add(new MoveCommand());

        RoverNode roverNode2 = new RoverNode(rover2, commands2);

        ArrayList<RoverThread> threads = new ArrayList<>();
        this.roverThread1 = new RoverThread(roverNode1, threads, locks);
        this.roverThread2 = new RoverThread(roverNode2, threads, locks);

        threads.add(this.roverThread1);
        threads.add(this.roverThread2);
    }

    @After
    public void after() {
        this.roverThread1 = null;
        this.roverThread2 = null;
    }

    /**
     * Test isBlock is false before execute the call method.
     */
    @Test
    public void isBlockIsFalse() {
        assertFalse(roverThread1.isBlock());
    }

    /**
     * Test blocked exception is thrown if one rover goes against other.
     */
    @Test(expected = BlockedException.class)
    public void isBlockIsTrue() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<Rover> future1 = executor.submit(roverThread1);
            Future<Rover> future2 = executor.submit(roverThread2);

            future1.get();
            future2.get();
        } catch(Exception e) {
            if(e.getCause() instanceof BlockedException) {
                BlockedException blocked = (BlockedException) e.getCause();
                throw blocked;
            }
            throw e;
        } finally {
            executor.shutdownNow();
        }
    }

    /**
     * Test new rover thread is not finished.
     */
    @Test
    public void isFinishedIsFalse() {
        assertFalse(roverThread1.isFinished());
    }

    /**
     * Test new rover thread is finished after call method execution.
     */
    @Test
    public void isFinishedIsTrue() throws Exception {
        Plateau plateau = new Plateau(new Point(5, 5));

        Map<Point, Lock> locks = new HashMap<>();
        for(Point point : plateau.getAllPoints()) {
            locks.put(point, new ReentrantLock());
        }

        Rover rover1 = new Rover(plateau, new Point(0, 0), Orientation.NORTH);

        ArrayList<Command> commands1 = new ArrayList<>();
        commands1.add(new MoveCommand());
        commands1.add(new SpinRightCommand());

        RoverNode roverNode1 = new RoverNode(rover1, commands1);

        ArrayList<RoverThread> threads = new ArrayList<>();
        RoverThread roverThread = new RoverThread(roverNode1, threads, locks);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        try {
            Future<Rover> future1 = executor.submit(roverThread);
            future1.get();

            assertTrue(roverThread.isFinished());
        } finally {
            executor.shutdownNow();
        }
    }
}
