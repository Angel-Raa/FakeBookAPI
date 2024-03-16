package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.persistence.repository.CommentRepository;
import com.github.angel.raa.modules.persistence.repository.PostRepository;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import com.github.angel.raa.modules.service.intefaces.PostService;
import com.github.angel.raa.modules.utils.DTO.PostDTO;
import com.github.angel.raa.modules.utils.DTO.PostWithCommentsDTO;
import com.github.angel.raa.modules.utils.api.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PostServiceImp implements PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Set<PostDTO> getAllPosts() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostDTO getPost(Long postId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Set<PostWithCommentsDTO> getPostWithComments() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<PostDTO> createPost(PostDTO postDTO, Long userId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<PostDTO> updatePost(PostDTO postDTO, Long postId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<PostDTO> deletePost(Long postId) {
        return null;
    }
}
