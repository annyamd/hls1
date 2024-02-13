package ru.itmo.hls1.controllers.exceptions;

public class TeamClosedException extends ControllerException{
    public TeamClosedException(String error, String message) {
        super(error, message);
    }
}
