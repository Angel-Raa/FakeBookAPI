package com.github.angel.raa.modules.utils.DTO;

import java.util.List;

public record PostWithCommentsDTO(
        Long postId,
        Long userId,
        String title,
        String content,
        List<CommentDTO> comments
) {
}
