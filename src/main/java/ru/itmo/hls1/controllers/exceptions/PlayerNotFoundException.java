package ru.itmo.hls1.controllers.exceptions;

public class PlayerNotFoundException extends NotFoundException{
    public PlayerNotFoundException(String filtersString) {
        super("player", filtersString);
    }
}
