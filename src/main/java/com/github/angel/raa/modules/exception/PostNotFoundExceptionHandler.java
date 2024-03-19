package com.github.angel.raa.modules.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundExceptionHandler extends SystemExceptionHandler {
    public PostNotFoundExceptionHandler(String message, Integer code) {
        super(message, code);
    }
}
