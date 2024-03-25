package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.dto.Login;
import com.github.angel.raa.modules.utils.dto.Sign;
import com.github.angel.raa.modules.utils.api.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Servicio para la autenticación y gestión de tokens de acceso.
 */
public interface AuthenticationService {
    /**
     * Realiza el inicio de sesión para un usuario.
     *
     * @param login El objeto Login que contiene las credenciales del usuario.
     * @return Una respuesta de autenticación que contiene el token de acceso y otros datos relacionados.
     */
    AuthenticationResponse login(Login login);
    /**
     * Registra a un nuevo usuario en el sistema.
     *
     * @param sign El objeto Sign que contiene la información de registro del nuevo usuario.
     * @return Una respuesta de autenticación que contiene el token de acceso y otros datos relacionados.
     */
    AuthenticationResponse register(Sign sign);
    /**
     * Verifica si un token de acceso es válido.
     *
     * @param token El token de acceso a verificar.
     * @return true si el token es válido, false si no lo es.
     */
    boolean isTokenValid(String token);
    /**
     * Genera un nuevo token de acceso (refresh token) basado en un token existente.
     *
     * @param token El token de acceso existente.
     * @return Una respuesta de autenticación que contiene el nuevo token de acceso y otros datos relacionados.
     */
    AuthenticationResponse refreshToken(String token);
    /**
     * Cierra la sesión del usuario y elimina el token de acceso.
     *
     * @param request La solicitud HTTP que contiene el token de acceso a eliminar.
     * @return Una respuesta de autenticación que indica el éxito de la operación de cierre de sesión.
     */
    AuthenticationResponse logout(HttpServletRequest request);
}
