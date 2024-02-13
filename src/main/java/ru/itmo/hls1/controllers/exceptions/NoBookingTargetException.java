package ru.itmo.hls1.controllers.exceptions;

public class NoBookingTargetException extends ControllerException{
    public NoBookingTargetException(String error, String message) {
        super(error, message);
    }
}
