package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.persistence.repository.CommentRepository;
import com.github.angel.raa.modules.persistence.repository.PostRepository;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import com.github.angel.raa.modules.service.intefaces.CommentService;
import com.github.angel.raa.modules.utils.DTO.CommentDTO;
import com.github.angel.raa.modules.utils.api.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Set<CommentDTO> getAllComments() {
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public CommentDTO getComment(Long commentId) {
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<CommentDTO> createComment(CommentDTO commentDTO, Long postId, Long userId) {
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<CommentDTO> updateComment(CommentDTO commentDTO, Long commentId) {
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<CommentDTO> deleteComment(Long commentId) {
        return null;
    }
}
