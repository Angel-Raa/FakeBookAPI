package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.PostDTO;
import com.github.angel.raa.modules.utils.DTO.PostWithCommentsDTO;
import com.github.angel.raa.modules.utils.api.Response;

import java.util.Set;
/**
 * Servicio para gestionar operaciones relacionadas con los posts.
 */
public interface PostService {
    /**
     * Obtiene todos los posts.
     *
     * @return Un conjunto de objetos PostDTO que representan todos los posts.
     */
    Set<PostDTO> getAllPosts();

    /**
     * Obtiene un post por su ID.
     *
     * @param postId El ID del post a obtener.
     * @return El objeto PostDTO que representa el post correspondiente al ID proporcionado.
     */

    PostDTO getPost(Long postId);
    /**
     * Obtiene todos los posts junto con sus comentarios.
     *
     * @return Un conjunto de objetos PostWithCommentsDTO que representan todos los posts con sus comentarios.
     */
    Set<PostWithCommentsDTO> getPostWithComments();

    /**
     * Crea un nuevo post.
     *
     * @param postDTO El objeto PostDTO que contiene los datos del nuevo post.
     * @param token  El ID del usuario que crea el post.
     * @return Una respuesta que encapsula el objeto PostDTO creado y un mensaje indicando el éxito de la operación.
     */
    Response<PostDTO> createPost(PostDTO postDTO, String token);

    /**
     * Actualiza un post existente.
     *
     * @param postDTO El objeto PostDTO que contiene los datos actualizados del post.
     * @param postId  El ID del post a actualizar.
     * @return Una respuesta que encapsula el objeto PostDTO actualizado y un mensaje indicando el éxito de la operación.
     */
    Response<PostDTO> updatePost(PostDTO postDTO, Long postId);

    /**
     * Elimina un post existente.
     *
     * @param postId El ID del post a eliminar.
     * @return Una respuesta que encapsula un mensaje indicando el éxito de la operación.
     */
    Response<PostDTO> deletePost(Long postId);
}
