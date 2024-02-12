package ru.itmo.hls1.controllers.exceptions.not_found;

public class BookingNotFoundException extends NotFoundException {
    public BookingNotFoundException(String filtersString) {
        super("booking", filtersString);
    }
}
