package com.devapp.pos.advisor;

import com.devapp.pos.exception.EntryNotfoundException;
import com.devapp.pos.exception.ValidationException;
import com.devapp.pos.util.StandardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(EntryNotfoundException.class)
    public ResponseEntity<StandardResponseDto> handleEntityNotFoundException(EntryNotfoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(StandardResponseDto.builder()
                        .code(404)
                        .message(ex.getMessage())
                        .data(ex)
                        .build());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardResponseDto> handleValidationException(ValidationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(StandardResponseDto.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .data(ex)
                        .build());
    }
}
