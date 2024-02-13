package ru.itmo.hls1.controllers.exceptions.handlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itmo.hls1.controllers.exceptions.ControllerException;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.controllers.exceptions.role.RoleNotGrantedException;
import ru.itmo.hls1.controllers.exceptions.UserAlreadyExistsException;
import ru.itmo.hls1.model.dto.ErrorDTO;
import ru.itmo.hls1.model.dto.ViolationDTO;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDTO handleNotFound(NotFoundException ex) {
        return new ErrorDTO(ex.getTimestamp(), ex.getMessage(), ex.getError());
    }

    @ExceptionHandler(RoleNotGrantedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorDTO handleNotGrantedRole(RoleNotGrantedException ex) {
        return new ErrorDTO(ex.getTimestamp(), ex.getMessage(), ex.getError());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorDTO handleAlreadyExists(UserAlreadyExistsException ex) {
        return new ErrorDTO(ex.getTimestamp(), ex.getMessage(), ex.getError());
    }

    @ExceptionHandler(ControllerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorDTO handleNotImpl(ControllerException ex) {
        return new ErrorDTO(ex.getTimestamp(), ex.getMessage(), ex.getError());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDTO handleNotValidBody(ConstraintViolationException ex) {
        List<ViolationDTO> violations = ex.getConstraintViolations().stream()
                .map(
                        violation -> new ViolationDTO(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .toList();

        return new ErrorDTO(
                LocalDateTime.now(),
                "Arguments of request (path or query) isn't valid. See violation list.",
                "Validation failed",
                violations
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorDTO handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ViolationDTO> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ViolationDTO(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ErrorDTO(
                LocalDateTime.now(),
                "Body of request isn't valid. See violation list.",
                "Validation failed",
                violations
        );
    }

}
