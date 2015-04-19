package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.command.Command;

import java.util.List;
import java.util.concurrent.Callable;

public class RoverThread implements Callable<Rover> {
    private Rover rover;

    private List<Command> commands;

    private int current;

    private BlockHandler handler;

    public RoverThread(Rover rover, List<Command> commands, BlockHandler handler) {
        this.rover = rover;
        this.commands = commands;
        this.current = 0;
        this.handler = handler;
    }

    @Override
    public Rover call() throws Exception {
        while(!isFinished()) {
            Command command = this.commands.get(current);
            if(command.canExecute()) {
                this.rover.execute(this.commands.get(current));
                this.current++;
            } else {
                handler.onBlock();
            }

        }

        return rover;
    }

    public boolean isFinished() {
        return this.current >= commands.size();
    }

    public boolean isBlock() {
        if(!isFinished()) {
            Command command = this.commands.get(current);
            return !command.canExecute();
        }
        return false;
    }

    public Rover getRover() {
        return this.rover;
    }
}
