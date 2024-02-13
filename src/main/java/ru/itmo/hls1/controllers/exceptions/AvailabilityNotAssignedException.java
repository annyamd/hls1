package ru.itmo.hls1.controllers.exceptions;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class AvailabilityNotAssignedException extends ControllerException {
    public AvailabilityNotAssignedException(String message) {
        super("error", message);
    }
}
