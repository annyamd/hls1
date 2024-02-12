package ru.itmo.hls1.controllers.exceptions.role;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class RoleNotGrantedException extends ControllerException {
    public RoleNotGrantedException(String message) {
        super("Role can't be granted", message);
    }
}
