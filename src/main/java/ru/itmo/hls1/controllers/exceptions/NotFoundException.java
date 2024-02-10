package ru.itmo.hls1.controllers.exceptions;

import java.time.LocalDateTime;

public class NotFoundException extends ControllerException {
    private final static String errorName = "Element Not Found";

    public NotFoundException(String objectType, String filtersString) {
        super(errorName, "no elements found (type: " + objectType + ", filters: " + filtersString + ")");
    }
}
