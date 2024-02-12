package ru.itmo.hls1.controllers.exceptions.not_found;

public class TeamNotFoundException extends NotFoundException{
    public TeamNotFoundException(String filtersString) {
        super("team", filtersString);
    }
}
