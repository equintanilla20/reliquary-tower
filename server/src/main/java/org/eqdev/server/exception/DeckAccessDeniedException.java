package org.eqdev.server.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeckAccessDeniedException extends RuntimeException {
    public DeckAccessDeniedException(String message) {
        super(message);
        log.error("DeckAccessDeniedException: {}", message);
    }
}
