package ru.itmo.hls1.controllers.exceptions.unavailable_action;


import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class PlaygroundNotAvailableException extends UnavailableActionException {
    public PlaygroundNotAvailableException(long playgroundId) {
        super("Playground (id = " + playgroundId + ") is not available (closed)");
    }
}
