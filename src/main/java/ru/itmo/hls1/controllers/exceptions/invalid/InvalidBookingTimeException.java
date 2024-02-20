package ru.itmo.hls1.controllers.exceptions.invalid;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class InvalidBookingTimeException extends InvalidRequestDataException {
    public InvalidBookingTimeException(String message) {
        super("Booking can't be added: " + message);
    }
}
