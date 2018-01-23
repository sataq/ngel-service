package org.ngel.station.common.exception;

/**
 * @author vgarg
 */
public class InvalidDateException extends RuntimeException {
    private static final long serialVersionUID = 7003520941844790210L;

    private static final String MESSAGE = "Invalid date provided.";

    public InvalidDateException() {
        super(MESSAGE);
    }
}
