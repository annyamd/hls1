package ru.itmo.hls1.controllers.exceptions;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class PlaygroundNotAvailableException extends ControllerException {
    public PlaygroundNotAvailableException(String error, String message) {
        super(error, message);
    }
}
