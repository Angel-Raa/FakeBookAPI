package com.github.angel.raa.modules.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Comment not found")
public class CommentNotFoundExceptionHandler extends SystemExceptionHandler {
    public CommentNotFoundExceptionHandler(String message, Integer code) {
        super(message, code);
    }
}
