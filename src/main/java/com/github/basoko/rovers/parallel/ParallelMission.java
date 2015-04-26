package com.github.basoko.rovers.parallel;

import com.github.basoko.rovers.NasaDataParser;
import com.github.basoko.rovers.Plateau;
import com.github.basoko.rovers.Point;
import com.github.basoko.rovers.Rover;
import com.github.basoko.rovers.exception.BlockedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        Plateau plateau = parser.getPlateau();

        Map<Point, Lock> locks = getLockPointsMap(plateau.getAllPoints());

        List<RoverThread> threads = new ArrayList<>();
        for(Rover rover : parser.getRovers()) {
            RoverNode node = new RoverNode(rover,  parser.getCommands(rover));
            threads.add(new RoverThread(node, threads, locks));
        }

        try {
            executor = Executors.newFixedThreadPool(threads.size());
            List<Future<Rover>> futures = executor.invokeAll(threads);

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

    private Map<Point, Lock> getLockPointsMap(List<Point> points) {
        Map<Point, Lock> locks = new HashMap<>();
        for(Point point : points) {
            locks.put(point, new ReentrantLock());
        }

        return locks;
    }

}
