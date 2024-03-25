package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.dto.InfoDTO;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.dto.UserDTO;

import java.util.Set;
/**
 * Servicio para la gestión de usuarios.
 */
public interface UserService {
    /**
     * Obtiene todos los usuarios.
     *
     * @return Un conjunto de objetos UserDTO que representan todos los usuarios.
     */
    Set<InfoDTO> getAllUsers();
    /**
     * Obtiene un usuario por su ID.
     *
     * @param userId El ID del usuario a obtener.
     * @return El objeto UserDTO que representa el usuario correspondiente al ID proporcionado.
     */
    InfoDTO getUserById(Long userId);
    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario a obtener.
     * @return El objeto UserDTO que representa el usuario correspondiente al nombre de usuario proporcionado.
     */
    InfoDTO getUserByUsername(String username);
    /**
     * Crea un nuevo usuario y lo agrega a una respuesta del tipo UserDTO en un Response.
     *
     * @param dto    El objeto UserDTO que contiene los datos del nuevo usuario.
     * @param token  El token de autenticación del usuario.
     * @return Una respuesta que encapsula el objeto UserDTO creado y un mensaje indicando el éxito de la operación.
     */
    Response<UserDTO> create(UserDTO dto, String token);
    /**
     * Actualiza el email de un usuario y agrega la información a una respuesta del tipo UserDTO en un Response.
     *
     * @param dto    El objeto UserDTO que contiene los datos del nuevo usuario.
     * @param token  El token de autenticación del usuario.
     * @return Una respuesta que encapsula el objeto UserDTO actualizado y un mensaje indicando el éxito de la operación.
     */
    Response<UserDTO> update(UserDTO dto, String token);

}
