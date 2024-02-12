package ru.itmo.hls1.controllers.exceptions.not_found;

public class PlaygroundAvailabilityNotFoundException extends NotFoundException{
    public PlaygroundAvailabilityNotFoundException(String filtersString) {
        super("playground availability", filtersString);
    }
}
