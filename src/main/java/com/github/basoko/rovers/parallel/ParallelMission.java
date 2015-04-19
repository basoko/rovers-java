package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.NasaDataParser;
import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.exception.BlockedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The parallel mission executor.
 */
public class ParallelMission {
    private ExecutorService executor;

    NasaDataParser parser;

    /**
     * Constructs a parallel mission.
     * @param parser The {@link NasaDataParser parser} with the mission data.
     */
    public ParallelMission(NasaDataParser parser) {
        this.parser = parser;
    }

    /**
     * Starts the mission.
     */
    public void start() {
        List<RoverThread> rovers = new ArrayList<>();

        BlockHandler handler = new BlockHandler(rovers);

        for(Rover rover : parser.getRovers()) {
            rovers.add(new RoverThread(rover, parser.getCommands(rover), handler));
        }

        try {
            executor = Executors.newFixedThreadPool(rovers.size());

            List<Future<Rover>> futures = new ArrayList<>();
            for (RoverThread rover : rovers) {
                Future<Rover> future = executor.submit(rover);
                futures.add(future);
            }

            for (Future<Rover> future : futures) {
                Rover rover = future.get();

                System.out.println(rover.showPosition());
            }

        } catch(Exception e) {
            if(e.getCause() instanceof BlockedException) {
                BlockedException blocked = (BlockedException) e.getCause();
                System.err.println(blocked.getMessage());
            } else {
                System.err.println("Error! The mission couldn't be completed!");
            }
        } finally {
            if(executor != null) {
                executor.shutdownNow();
            }
        }
    }
}
