package ru.itmo.hls1.controllers.exceptions.already_applied;

import ru.itmo.hls1.controllers.exceptions.ControllerException;

public class UserAlreadyExistsException extends AlreadyAppliedException {
    public UserAlreadyExistsException(String login) {
        super("User with login = '" + login + "' already exists.");
    }
}
