package ru.itmo.hls1.controllers.exceptions.not_found;

public class SportNotFoundException extends NotFoundException {

    public SportNotFoundException(String filtersString) {
        super("sport", filtersString);
    }
}
