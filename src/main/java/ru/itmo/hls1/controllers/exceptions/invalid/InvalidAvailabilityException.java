package ru.itmo.hls1.controllers.exceptions.invalid;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class InvalidAvailabilityException extends InvalidRequestDataException {
    public InvalidAvailabilityException(String reason) {
        super("Can't set such pg availability, reason: " + reason);
    }
}
