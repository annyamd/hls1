package ru.itmo.hls1.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ControllerException extends RuntimeException {

    private final LocalDateTime timestamp;
    private final String message;
    private final String error;

    public ControllerException(String error, String message) {
        timestamp = LocalDateTime.now();
        this.message = message;
        this.error = error;
    }
}
