package com.github.basoko.rovers;

import com.github.basoko.rovers.command.Command;
import com.github.basoko.rovers.parallel.ParallelMission;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Application that represents the Nasa Mars rovers mission.
 */
public class Application {

    /**
     * The main of the application.
     * The application receives only one argument that points to the instructions file.
     * @param args Array string of arguments passed to the application.
     */
    public static void main(String[] args) {
        try {
            checkArguments(args);

            String filename = args[0];
            Path filePath = Paths.get(filename);

            checkFile(filePath);

            NasaDataParser parser = new NasaDataParser(filePath);
            ParallelMission mission = new ParallelMission(parser);

            mission.start();
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void checkArguments(String[] args) {
        if(args.length != 1) {
            throw new IllegalArgumentException("Bad number of arguments passed to the mission.");
        }
    }

    private static void checkFile(Path filePath) {
        if(!Files.exists(filePath)) {
            throw new IllegalArgumentException("Not found file: " + filePath.toString());
        }
        if(!Files.exists(filePath)) {
            throw new IllegalArgumentException("Can't read file: " + filePath.toString());
        }
    }
}
