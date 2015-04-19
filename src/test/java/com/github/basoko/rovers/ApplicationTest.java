package com.github.basoko.rovers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Test suite of {@link Application}.
 */
public class ApplicationTest {

    private PrintStream oldStdOut;

    private PrintStream oldStdErr;

    private ByteArrayOutputStream stdOutput;

    private ByteArrayOutputStream errOutput;

    @Before
    public void before() {
        stdOutput = new ByteArrayOutputStream();
        PrintStream stdPs = new PrintStream(stdOutput);
        oldStdOut = System.out;
        System.setOut(stdPs);

        errOutput = new ByteArrayOutputStream();
        PrintStream errPs = new PrintStream(errOutput);
        oldStdErr = System.err;
        System.setErr(errPs);
    }

    @After
    public void after() {
        System.out.flush();
        System.setOut(oldStdOut);

        System.err.flush();
        System.setErr(oldStdErr);
    }

    /**
     * Test that if the application receives more that one argument should render a bad arguments error message.
     */
    @Test
    public void testBadNumberOfArguments() {
        Application.main(new String[]{ "files/input/test1_input.txt", "argument"});

        assertEquals("Bad number of arguments passed to the mission." , errOutput.toString().trim());
    }

    /**
     * Test that if the application receives an invalid input file should render a not found file error message.
     */
    @Test
    public void testInputFileNotExists() {
        String filename = "files/input/test.txt";
        Application.main(new String[]{filename});

        assertEquals("Not found file: " + filename, errOutput.toString().trim());
    }

    /**
     * Test that the application returns the correct output.
     */
    @Test
    public void testOutput() {
        // Print some output: goes to your special stream
        Application.main(new String[]{"files/tests/test1_input.txt"});

        String test = getTestOutput("files/tests/test1_output.txt");

        assertEquals(test.trim(), stdOutput.toString().trim());
    }

    private String getTestOutput(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
