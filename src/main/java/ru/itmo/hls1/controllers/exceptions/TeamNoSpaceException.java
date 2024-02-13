package ru.itmo.hls1.controllers.exceptions;

public class TeamNoSpaceException extends ControllerException{
    public TeamNoSpaceException() {
        super("Team has no space", "Can't add player to team, it's full.");
    }
}
