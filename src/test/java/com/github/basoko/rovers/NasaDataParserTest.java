package com.github.basoko.rovers;

import com.github.basoko.rovers.command.Command;
import com.github.basoko.rovers.command.MoveCommand;
import com.github.basoko.rovers.command.SpinLeftCommand;
import com.github.basoko.rovers.command.SpinRightCommand;
import com.github.basoko.rovers.exception.ParseException;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test suite of {@link NasaDataParser}.
 */
public class NasaDataParserTest {

    /**
     * Test that the parser get the plateau definition from the data.
     */
    @Test
    public void testParsePlateauData() {
        NasaDataParser parser = new NasaDataParser(Paths.get("files/tests/test1_input.txt"));
        Plateau plateau = parser.getPlateau();

        assertEquals(new Plateau(new Point(5, 5)), plateau);
    }

    /**
     * Test that the parser get the rovers definition from the data.
     */
    @Test
    public void testParseRoversData() {
        NasaDataParser parser = new NasaDataParser(Paths.get("files/tests/test1_input.txt"));
        Plateau plateau = parser.getPlateau();
        assertEquals(2, parser.getRovers().size());

        Rover rover1 = parser.getRovers().get(0);
        assertEquals(new Rover(plateau, new Point(1, 2), Orientation.NORTH), rover1);

        Rover rover2 = parser.getRovers().get(1);
        assertEquals(new Rover(plateau, new Point(3, 3), Orientation.EAST), rover2);

        List<Command> commands1 = Arrays.asList(new Command[] {
                left(), move(), left(), move(),
                left(), move(), left(), move(),
                move()
        });
        assertTwoListOfCommands(commands1, parser.getCommands(rover1));

        List<Command> commands2 = Arrays.asList(new Command[] {
                move(), move(), right(), move(),
                move(), right(), move(), right(),
                right(), move()
        });
        assertTwoListOfCommands(commands2, parser.getCommands(rover2));
    }

    /**
     * Test that the parser throws an {@link com.github.basoko.rovers.exception.ParseException exception}
     * if the data are invalid.
     */
    @Test(expected = ParseException.class)
    public void testParseInvalidData() {
        NasaDataParser parser = new NasaDataParser(Paths.get("files/tests/invalid_data.txt"));
    }

    private SpinRightCommand right() {
        return new SpinRightCommand();
    }

    private SpinLeftCommand left() {
        return new SpinLeftCommand();
    }

    private MoveCommand move() {
        return new MoveCommand();
    }

    private void assertTwoListOfCommands(List<Command> commands1, List<Command> commands2) {
        Plateau plateau = new Plateau(new Point(5, 5));
        Rover rover1 = new Rover(plateau, new Point(1, 2), Orientation.NORTH);
        Rover rover2 = rover1;
        rover1 = executeListOfCommands(rover1, commands1);
        rover2 = executeListOfCommands(rover2, commands2);

        assertEquals(rover1, rover2);
    }

    private Rover executeListOfCommands(Rover rover, List<Command> commands) {
        for(Command command : commands) {
            rover = rover.execute(command);
        }
        return rover;
    }
}
