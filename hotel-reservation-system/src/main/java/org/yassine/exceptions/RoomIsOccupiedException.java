package org.yassine.exceptions;

public class RoomIsOccupiedException extends RuntimeException {
    public RoomIsOccupiedException(String message) {
        super(message);
    }
}
