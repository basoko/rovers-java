package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.Point;
import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.exception.BlockedException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

/**
 * A rover thread to process all the commands of the {@link com.github.basoko.rovers.Rover rover}.
 */
public class RoverThread implements Callable<Rover> {

    private RoverNode node;

    private boolean block;

    private List<RoverThread> rovers;

    private Map<Point, Lock> locks;


    /**
     * Constructs a new {@link Rover rover} thread
     * @param node The initial {@link RoverNode rover node}.
     * @param rovers The list of {@link RoverThread rover threads} in the system.
     * @param locks The map {@link Lock point locks} to synchronize the movement of a rover.
     */
    public RoverThread(RoverNode node, List<RoverThread> rovers, Map<Point, Lock> locks) {
        this.node = node;
        this.rovers = rovers;
        this.locks = locks;

        this.block = false;
    }

    @Override
    public Rover call() throws Exception {
        this.locks.get(this.node.getRover().getPosition()).lock();

        while(node.hasNext()) {
            RoverNode next = node.next();
            if(next.hasMoved()) {
                 onChange(next);
            } else{
                node = next;
            }
        }

        return node.getRover();
    }

    /**
     * Return true if thread can't go to the next rover node.
     * @return true if the thread is blocked otherwise false.
     */
    public boolean isBlock() {
        return this.block;
    }

    /**
     * Return true if thread has reached the last rover node.
     * @return true if the thread has reached the final node.
     */
    public boolean isFinished() {
        return !this.node.hasNext();
    }

    private void onChange(RoverNode node) {
        Lock oldLock = this.locks.get(this.node.getRover().getPosition());
        Lock lock = this.locks.get(node.getRover().getPosition());

        if(lock.tryLock()) {
            if(!isOccupied(node.getRover().getPosition())) {
                this.node = node;
                oldLock.unlock();
            } else {
                lock.unlock();
            }

            this.block = false;
        } else {
            this.block =  true;
            // Check finished and blocked
            if(isDeadLock()) {
                throw new BlockedException("Nasa Mars mission has failed! Rovers got blocked!");
            }
        }

    }

    private boolean isDeadLock() {
        boolean deadLock = true;
        for(RoverThread roverThread : rovers) {
            if(!roverThread.node.equals(this.node) &&
                    (!roverThread.isFinished() || roverThread.isBlock())) {
                deadLock &= true;
            }
        }
        return deadLock;
    }

    private boolean isOccupied(Point point) {
        for(RoverThread roverThread : rovers) {
            if(point.equals(roverThread.node.getRover().getPosition())) {
                return true;
            }
        }
        return false;
    }
}
