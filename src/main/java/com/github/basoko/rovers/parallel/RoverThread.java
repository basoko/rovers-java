package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.command.Command;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * A rover thread to process all the commands of the {@link Rover rover}.
 */
public class RoverThread implements Callable<Rover> {
    private Rover rover;

    private List<Command> commands;

    private int current;

    private BlockHandler handler;

    /**
     * Constructs a new {@link Rover rover} thread
     * @param rover The {@link Rover rover} to move.
     * @param commands The list of {@link Command commands} to be executed on the rover.
     * @param handler A {@link BlockHandler block handler} to notify that it can not execute a {@link Command command}.
     */
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

    /**
     * Return true if it has finished the execution of {@link Command commands}.
     * @return true if all the commands of the rover are invoked otherwise false.
     */
    public boolean isFinished() {
        return this.current >= commands.size();
    }

    /**
     * Return true if it can not execute the appropriate {@link Command command}.
     * @return true if it's blocked otherwise false.
     */
    public boolean isBlock() {
        if(!isFinished()) {
            Command command = this.commands.get(current);
            return !command.canExecute();
        }
        return false;
    }
}
