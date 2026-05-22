package com.devapp.pos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntryNotfoundException extends RuntimeException {
    public EntryNotfoundException(String message) {
        super(message);
    }
}
