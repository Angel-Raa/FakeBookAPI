package com.github.angel.raa.modules.utils.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty(message = "El campo de nombre de usuario no puede estar vacío.")
        @Size(max = 25, message = "El nombre debe tener como máximo 25 caracteres.")
        @Pattern(regexp = "^[a-zA-Z ]*$", message = "El nombre solo puede contener letras y espacios.")
        String name,

        @Size(max = 1000, message = "La biografía debe tener como máximo 1000 caracteres.")
        String bio



) {
}
