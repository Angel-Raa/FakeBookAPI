package com.github.angel.raa.modules.utils.DTO;

import java.time.LocalDateTime;

public record UserDTO(Long userId, String username, String email, String photo,
                      String bio, LocalDateTime createAt, LocalDateTime updateAt) {
}
