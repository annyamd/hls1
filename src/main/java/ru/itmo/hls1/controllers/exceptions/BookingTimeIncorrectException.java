package ru.itmo.hls1.controllers.exceptions;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class BookingTimeIncorrectException extends ControllerException {
    public BookingTimeIncorrectException(String message) {
        super("", message);
    }
}
