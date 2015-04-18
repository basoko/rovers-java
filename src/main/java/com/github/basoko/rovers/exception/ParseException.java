package com.github.basoko.rovers.exception;

/**
 * Thrown by the {@link com.github.basoko.rovers.NasaDataParser parser} to indicate a parsing problem.
 */
public class ParseException extends RuntimeException {

    /**
     * Constructs parse exception.
     * @param message The detail message.
     */
    public ParseException(String message) {
        super(message);
    }
}
