package com.github.angel.raa.modules.utils.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ImageDTO(
        @NotNull
        String token,
                       @NotNull
                       MultipartFile file) {
}
