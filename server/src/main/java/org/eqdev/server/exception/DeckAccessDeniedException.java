package org.eqdev.server.exception;

public class DeckAccessDeniedException extends RuntimeException {
    public DeckAccessDeniedException(String message) {
        super(message);
    }
}
