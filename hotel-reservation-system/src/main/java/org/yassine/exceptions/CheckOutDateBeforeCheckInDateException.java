package org.yassine.exceptions;

public class CheckOutDateBeforeCheckInDateException extends RuntimeException {
    public CheckOutDateBeforeCheckInDateException(String message) {
        super(message);
    }
}
