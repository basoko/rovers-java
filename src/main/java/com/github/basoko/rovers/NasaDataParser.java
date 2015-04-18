package com.github.basoko.rovers;

import com.github.basoko.rovers.command.Command;
import com.github.basoko.rovers.command.CommandFactory;
import com.github.basoko.rovers.exception.ParseException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Parser of the Nasa data.
 */
public class NasaDataParser {

    private final Path filePath;

    private Plateau plateau;

    private ArrayList<Rover> rovers;

    private HashMap<Rover, List<Command>> commands;

    /**
     * Creates a parser to parse the Nasa data.
     * @param filePath The file path with the instructions.
     */
    public NasaDataParser(Path filePath) {
        this.rovers = new ArrayList<>();
        this.commands = new HashMap<>();

        this.filePath = filePath;

        this.parse();
    }

    /**
     * Get the {@link Plateau plateau} from the parsed data.
     * @return the plateau.
     */
    public Plateau getPlateau() {
        return this.plateau;
    }

    /**
     * Get the list of {@link Rover rovers} from the parsed data.
     * @return the list of rovers.
     */
    public List<Rover> getRovers() {
        return this.rovers;
    }

    /**
     * Get the list of {@link Command commands} of the {@link Rover rover} from the parsed data.
     * @return the list of {@link Command commands}.
     */
    public List<Command> getCommands(Rover rover) {
        return this.commands.get(rover);
    }

    private void parse() {
        try {
            List<String> instructions = readFile();

            parsePlateau(instructions.get(0));
            for (int i = 1; i < instructions.size(); i += 2) {
                parseRovers(instructions.get(i), instructions.get(i + 1));
            }
        } catch(Exception e) {
            throw new ParseException(e.getMessage());
        }
    }

    private List<String> readFile() throws IOException {
        List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
        return lines;
    }

    private static Point getPoint(String line) {
        String[] data = line.split(" ");

        int x = Integer.parseInt(data[0]);
        int y = Integer.parseInt(data[1]);

        return new Point(x, y);
    }

    private void parsePlateau(String plateauData) {
        Point plateauPoint = getPoint(plateauData);
        this.plateau = new Plateau(plateauPoint);
    }

    private void parseRovers(String roverData, String commandsData) {
        Point roverPosition = getPoint(roverData);
        Orientation roverOrientation = getOrientation(roverData);
        Rover rover = new Rover(plateau, roverPosition, roverOrientation);
        List<Command> commands = parseCommands(rover, commandsData);

        this.rovers.add(rover);
        this.commands.put(rover, commands);
    }

    private Orientation getOrientation(String line) {
        String[] data = line.split(" ");

        return Orientation.fromInitial(data[2].charAt(0));
    }

    private static ArrayList<Command> parseCommands(Rover rover, String line) {
        ArrayList<Command> commands = new ArrayList<>();
        for(char letter : line.toCharArray()) {
            Command command = CommandFactory.newInstance(rover, letter);
            commands.add(command);
        }
        return commands;
    }
}
