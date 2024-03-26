package com.github.angel.raa.modules.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CommentDTO(Long commentId,
                         @NotBlank(message = "El contenido del comentario no puede estar vacío.")
                         @Size(max = 1000, message = "El contenido del comentario debe tener como máximo 1000 caracteres.")
                         @Pattern(regexp = "^[a-zA-Z0-9 .,!?'\"-]*$", message = "El contenido del comentario solo puede contener letras, números y los siguientes caracteres especiales: .,!?'\"-")
                         String content,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                         LocalDateTime createdAt,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                         LocalDateTime updatedAt ) {
}
