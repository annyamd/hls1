package ru.itmo.hls1.controllers.exceptions;

public class TeamManagerNotHisTeamException extends ControllerException{
    public TeamManagerNotHisTeamException(long teamId, long teamManagerId) {
        super("Team manager cant do such actions", "");
    }
}
