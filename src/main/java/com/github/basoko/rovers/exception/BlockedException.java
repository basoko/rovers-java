package com.github.basoko.rovers.exception;

/**
 * Thrown by the {@link com.github.basoko.rovers.parallel.RoverThread rover execution thread}.
 * if the system gets blocked.
 */
public class BlockedException extends RuntimeException {

    /**
     * Constructs blocked exception.
     * @param message The detail message.
     */
    public BlockedException(String message) {
        super(message);
    }
}
