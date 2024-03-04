package com.github.angel.raa.modules.utils.DTO;

public record UserDTO(String userId, String username, String email, String photo,
                      String bio, String createAt, String updateAt) {
}
