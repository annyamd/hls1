package ru.itmo.hls1.controllers.exceptions;

public class PlaygroundAvailabilityNotFoundException extends NotFoundException{
    public PlaygroundAvailabilityNotFoundException(String filtersString) {
        super("playground availability", filtersString);
    }
}
