package com.github.angel.raa.modules.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SystemExceptionHandler extends RuntimeException {
    private String message;
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public SystemExceptionHandler(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

}
