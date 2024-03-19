package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.UserDTO;

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
    Set<UserDTO> getAllUsers();
    /**
     * Obtiene un usuario por su ID.
     *
     * @param userId El ID del usuario a obtener.
     * @return El objeto UserDTO que representa el usuario correspondiente al ID proporcionado.
     */
    UserDTO getUserById(Long userId);
    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario a obtener.
     * @return El objeto UserDTO que representa el usuario correspondiente al nombre de usuario proporcionado.
     */
    UserDTO getUserByUsername(String username);

}
