package ru.itmo.hls1.controllers.exceptions.handlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.hls1.controllers.exceptions.NotFoundException;
import ru.itmo.hls1.model.dto.ErrorDTO;
import ru.itmo.hls1.model.dto.ViolationDTO;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDTO handleNotFound(NotFoundException ex) {
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
