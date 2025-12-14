package org.yassine.exceptions;

public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException(String message) {
        super(message);
    }
}
