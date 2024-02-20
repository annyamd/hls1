package ru.itmo.hls1.controllers.exceptions.invalid;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class NoBookingTargetException extends InvalidRequestDataException {
    public NoBookingTargetException() {
        super("No booking target. Add not null player or team.");
    }
}
