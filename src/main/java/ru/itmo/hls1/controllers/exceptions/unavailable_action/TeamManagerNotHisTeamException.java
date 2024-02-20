package ru.itmo.hls1.controllers.exceptions.unavailable_action;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class TeamManagerNotHisTeamException extends UnavailableActionException {
    public TeamManagerNotHisTeamException(long teamManagerId, long teamId) {
        super("Team manager (id = " + teamManagerId
                + ") can't do such actions with team (id = " + teamId + "), it is not his team.");
    }
}
