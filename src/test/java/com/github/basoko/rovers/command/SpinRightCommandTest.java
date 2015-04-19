package com.github.basoko.rovers.command;

import com.github.basoko.rovers.Rover;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Test suite of {@link SpinRightCommand}.
 */
@RunWith(MockitoJUnitRunner.class)
public class SpinRightCommandTest {

    @Mock
    private Rover rover;

    /**
     * Test the rover spins right executing the command.
     */
    @Test
    public void testSpinRight() {
        SpinRightCommand command = new SpinRightCommand(rover);

        command.execute();

        verify(rover).spinRight();
    }
}
