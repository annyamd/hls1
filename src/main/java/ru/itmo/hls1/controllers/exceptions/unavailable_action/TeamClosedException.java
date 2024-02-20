package ru.itmo.hls1.controllers.exceptions.unavailable_action;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class TeamClosedException extends UnavailableActionException {
    public TeamClosedException(long teamId) {
        super("Can't add player to team (id = " + teamId + "), it is closed -- only it's team manager can add players.");
    }
}
