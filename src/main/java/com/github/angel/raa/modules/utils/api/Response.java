package com.github.angel.raa.modules.utils.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * Clase genérica para representar las respuestas de la API, facilitando la estandarización
 * del formato de respuesta a través de los diferentes endpoints.
 *
 * @param <T> El tipo de dato del cuerpo de la respuesta.
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 102918L;
    @NotNull(message = "El mensaje no puede ser nulo")
    private String message;
    @NotNull(message = "El código de estado no puede ser nulo")
    private HttpStatus status;
    private boolean success;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private T data;
}
