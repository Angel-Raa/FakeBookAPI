package com.github.angel.raa.modules.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundExceptionHandler extends SystemExceptionHandler {
    public UserNotFoundExceptionHandler(String message, Integer code) {
        super(message, code);
    }
}
