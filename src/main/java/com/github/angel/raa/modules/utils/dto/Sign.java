package com.github.angel.raa.modules.utils.dto;

import jakarta.validation.constraints.*;


public record Sign(
        @NotBlank(message = "No puede haber espacios en blanco en la username.")
        @NotNull(message = "El campo de nombre de usuario no puede ser nulo.")
        @NotEmpty(message = "El campo de username no puede estar vacío.")
        @Size(min = 4, max = 25, message = "El nombre de usuario debe tener entre 4 y 25 caracteres.")
        String username,

        @NotNull(message = "El campo de contraseña no puede ser nulo.")
        @NotBlank(message = "No puede haber espacios en blanco en la contraseña.")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número, un carácter especial y no puede contener espacios en blanco.")
        String password,

        @NotNull(message = "El campo de correo electrónico no puede ser nulo.")
        @NotEmpty(message = "El campo de correo electrónico no puede estar vacío.")
        @Email(message = "Debe ingresar un correo electrónico válido.")
        String email

) {
}
