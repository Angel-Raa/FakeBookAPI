package com.github.angel.raa.modules.service.intefaces;


import com.github.angel.raa.modules.exception.CommentNotFoundExceptionHandler;
import com.github.angel.raa.modules.utils.dto.CommentDTO;
import com.github.angel.raa.modules.utils.api.Response;

import java.util.Set;

/**
 * Servicio para gestionar operaciones relacionadas con los comentarios.
 */
public interface CommentService {
    /**
     * Obtiene todos los comentarios.
     *
     * @return Un conjunto de objetos CommentDTO que representan todos los comentarios.
     */
    Set<CommentDTO> getAllComments();
    /**
     * Obtiene un comentario por su ID.
     *
     * @param commentId El ID del comentario a obtener.
     * @return El objeto CommentDTO que representa el comentario correspondiente al ID proporcionado.
     * @throws CommentNotFoundExceptionHandler Si no se encuentra ningún comentario con el ID proporcionado.
     */
    CommentDTO getComment(Long commentId);

    /**
     * Crea un nuevo comentario y lo asocia a un post y usuario.
     *
     * @param commentDTO El objeto CommentDTO que contiene los datos del nuevo comentario.
     * @param postId     El ID del post al que se asocia el comentario.
     * @return Una respuesta que encapsula el objeto CommentDTO creado y un mensaje indicando el éxito de la operación.
     * @throws CommentNotFoundExceptionHandler Si no se encuentra ningún post con el ID proporcionado o ningún usuario con el ID proporcionado.
     */
    Response<CommentDTO> createComment(CommentDTO commentDTO, Long postId, String token);

    /**
     * Actualiza un comentario existente.
     *
     * @param commentDTO El objeto CommentDTO que contiene los datos actualizados del comentario.
     * @param commentId  El ID del comentario a actualizar.
     * @return Una respuesta que encapsula el objeto CommentDTO actualizado y un mensaje indicando el éxito de la operación.
     * @throws CommentNotFoundExceptionHandler Si no se encuentra ningún comentario con el ID proporcionado.
     */
    Response<CommentDTO> updateComment(CommentDTO commentDTO, Long commentId);

    /**
     * Elimina un comentario existente.
     *
     * @param commentId El ID del comentario a eliminar.
     * @return Una respuesta que encapsula un mensaje indicando el éxito de la operación.
     */
    Response<CommentDTO> deleteComment(Long commentId);
}
