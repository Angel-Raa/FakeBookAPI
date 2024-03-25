package com.github.angel.raa.modules.utils.dto;

import java.time.LocalDateTime;

public record InfoDTO(Long userId, String username, String name, String email, String photo,
                      String bio, LocalDateTime createAt, LocalDateTime updateAt) {
}
