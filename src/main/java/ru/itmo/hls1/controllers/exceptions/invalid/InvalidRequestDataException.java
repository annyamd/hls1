package ru.itmo.hls1.controllers.exceptions.invalid;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class InvalidRequestDataException extends ControllerException {
    public InvalidRequestDataException(String message) {
        super("Invalid request", message);
    }
}