package com.github.angel.raa.modules.utils.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Login(
        @NotBlank(message = "No puede haber espacios en blanco en la username.")
        @NotNull(message = "El campo de nombre de usuario no puede ser nulo.")
        @NotEmpty(message = "El campo de username no puede estar vacío.")
        @Size(min = 4, max = 25, message = "El nombre de usuario debe tener entre 4 y 25 caracteres.")
        String username,

        @NotBlank(message = "No puede haber espacios en blanco en la contraseña.")
        @NotNull(message = "El campo de contraseña no puede estar null. ")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
        String password) {
}
