package org.ngel.station.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author vgarg
 */
@Slf4j
public class InvalidDateRangeException extends RuntimeException {
    private static final long serialVersionUID = -1373862173639257383L;

    public InvalidDateRangeException(String message) {
        super(message);
        log.error("Invalid date range provided. {}", message);
    }
}
