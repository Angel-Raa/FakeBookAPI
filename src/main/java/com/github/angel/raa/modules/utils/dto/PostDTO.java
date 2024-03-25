package com.github.angel.raa.modules.utils.dto;

import java.time.LocalDateTime;

public record PostDTO(Long postId, String title, String content, LocalDateTime publishedAt, LocalDateTime updatedAt) {
}
