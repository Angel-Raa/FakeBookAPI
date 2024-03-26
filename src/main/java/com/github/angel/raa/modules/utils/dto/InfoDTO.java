package com.github.angel.raa.modules.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record InfoDTO(Long userId,
                      String username,
                      String name,
                      String email,
                      String photo,
                      String bio,
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                      LocalDateTime createAt,
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                      LocalDateTime updateAt) {
}
