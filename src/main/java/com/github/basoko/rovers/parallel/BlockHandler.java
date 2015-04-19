package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.exception.BlockedException;

import java.util.List;

public class BlockHandler {
    private List<RoverThread> rovers;

    public BlockHandler(List<RoverThread> rovers) {
        this.rovers = rovers;
    }

    public void onBlock(RoverThread roverThread) {
        boolean block = true;

        for(RoverThread each : this.rovers) {

            Rover rover = each.getRover();
            if(!rover.equals(roverThread.getRover())) {
                block = block && (each.isBlock() || each.isFinished());
            }
        }

        if(block) {
            throw new BlockedException("Nasa Mars mission failed! Rovers got blocked!");
        }
    }
}
