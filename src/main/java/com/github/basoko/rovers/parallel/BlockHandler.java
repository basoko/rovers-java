package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.exception.BlockedException;

import java.util.List;

public class BlockHandler {
    private List<RoverThread> rovers;

    public BlockHandler(List<RoverThread> rovers) {
        this.rovers = rovers;
    }

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
