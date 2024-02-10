package ru.itmo.hls1.controllers.exceptions;

public class PlaygroundNotFoundException extends NotFoundException {
    public PlaygroundNotFoundException(String filtersString) {
        super("playground", filtersString);
    }
}
