package com.github.angel.raa.modules.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PostDTO(Long postId,
                      @NotNull(message = "El campo de título no puede ser nulo.")
                      @NotEmpty(message = "El campo de título no puede estar vacío.")
                      @Size(max = 255, message = "El título debe tener como máximo 255 caracteres.")
                      String title,

                      @Size(max = 2000, message = "El contenido debe tener como máximo 2000 caracteres.")
                      @NotBlank(message = "El contenido no puede estar vacío.")
                      String content,

                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                      LocalDateTime publishedAt,
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                      LocalDateTime updatedAt) {
}
