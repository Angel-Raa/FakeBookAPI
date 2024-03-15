package com.github.angel.raa.modules.utils.DTO;

import java.time.LocalDateTime;

public record CommentDTO(Long commentId, String content, LocalDateTime createdAt, LocalDateTime updatedAt ) {
}
