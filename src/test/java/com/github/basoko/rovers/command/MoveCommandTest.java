package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Test suite of {@link MoveCommand}.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoveCommandTest {

    @Mock
    private Rover rover;

    /**
     * Test the rover moves executing the command.
     */
    @Test
    public void testMove() {
        MoveCommand command = new MoveCommand(rover);

        command.execute();

        verify(rover).move();
    }
}
