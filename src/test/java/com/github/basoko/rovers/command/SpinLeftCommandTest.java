package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Test suite of {@link SpinLeftCommand}.
 */
@RunWith(MockitoJUnitRunner.class)

public class SpinLeftCommandTest {

    @Mock
    private Rover rover;

    /**
     * Test the rover spins left executing the command.
     */
    @Test
    public void testSpinLeft() {
        SpinLeftCommand command = new SpinLeftCommand();

        command.execute(rover);

        verify(rover).spinLeft();
    }
}
