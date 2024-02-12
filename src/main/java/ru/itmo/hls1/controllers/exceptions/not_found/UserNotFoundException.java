package ru.itmo.hls1.controllers.exceptions.not_found;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String filtersString) {
        super("user", filtersString);
    }
}
