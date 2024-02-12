package ru.itmo.hls1.controllers.exceptions;

public class UserAlreadyExistsException extends ControllerException{
    public UserAlreadyExistsException(String login) {
        super("User already exists", "User with login = '" + login + "' already exists. Choose another login and try again.");
    }
}
