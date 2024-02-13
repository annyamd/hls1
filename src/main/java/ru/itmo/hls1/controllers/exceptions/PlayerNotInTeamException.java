package ru.itmo.hls1.controllers.exceptions;

public class PlayerNotInTeamException extends ControllerException{
    public PlayerNotInTeamException(String error, String message) {
        super(error, message);
    }
}
