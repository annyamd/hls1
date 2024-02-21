package ru.itmo.hls1.controllers.exceptions.already_applied;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class AlreadyAppliedException  extends ControllerException {
    public AlreadyAppliedException(String message) {
        super("Already applied action", message);
    }
}
