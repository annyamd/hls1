package ru.itmo.hls1.controllers.exceptions.unavailable_action;


import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class UnavailableActionException extends ControllerException {
    public UnavailableActionException(String message) {
        super("Action is unavailable", message);
    }
}
