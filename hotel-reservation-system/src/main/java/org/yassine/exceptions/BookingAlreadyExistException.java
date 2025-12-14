package org.yassine.exceptions;

public class BookingAlreadyExistException extends RuntimeException {
    public BookingAlreadyExistException(String message) {
        super(message);
    }
}
