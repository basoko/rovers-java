package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.exception.BlockedException;

import java.util.List;

/**
 * Handler to manage block situations.
 */
public class BlockHandler {
    private List<RoverThread> rovers;

    /**
     * Construct a new handler with all the rover threads.
     * @param rovers The rover threads running in the system.
     */
    public BlockHandler(List<RoverThread> rovers) {
        this.rovers = rovers;
    }

    /**
     * Callback method to manage the block situation.
     * When a rover thread can not execute its appropriate command
     * this method will be invoked.
     */
    public void onBlock() {
        boolean block = true;

        for (RoverThread each : this.rovers) {
            block = block && (each.isBlock() || each.isFinished());
        }

        if(block) {
            throw new BlockedException("Nasa Mars mission failed! Rovers got blocked!");
        }
    }
}