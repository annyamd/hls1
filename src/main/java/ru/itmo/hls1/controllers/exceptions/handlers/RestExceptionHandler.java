package ru.itmo.hls1.controllers.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.hls1.controllers.exceptions.ControllerException;
import ru.itmo.hls1.controllers.exceptions.NotFoundException;
import ru.itmo.hls1.model.dto.ErrorDTO;
import ru.itmo.hls1.sevice.util.Mapper;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final ErrorMapper mapper = new ErrorMapper();

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorDTO> handleNotFound(NotFoundException ex) {
        ErrorDTO errorDTO = mapper.entityToDto(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    static class ErrorMapper implements Mapper<ControllerException, ErrorDTO> {

        @Override
        public ErrorDTO entityToDto(ControllerException entity) {
            return new ErrorDTO(entity.getTimestamp(), entity.getMessage(), entity.getError());
        }

        @Override
        public ControllerException dtoToEntity(ErrorDTO dto) {
            return null;
        }
    }

}
