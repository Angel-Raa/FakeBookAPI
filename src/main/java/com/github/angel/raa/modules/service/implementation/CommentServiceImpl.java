package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.service.intefaces.CommentService;
import com.github.angel.raa.modules.utils.DTO.CommentDTO;
import com.github.angel.raa.modules.utils.api.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Override
    public Set<CommentDTO> getAllComments() {
        return null;
    }

    @Override
    public CommentDTO getComment(Long commentId) {
        return null;
    }

    @Override
    public Response<CommentDTO> createComment(CommentDTO commentDTO, Long postId, Long userId) {
        return null;
    }

    @Override
    public Response<CommentDTO> updateComment(CommentDTO commentDTO, Long commentId) {
        return null;
    }

    @Override
    public Response<CommentDTO> deleteComment(Long commentId) {
        return null;
    }
}
