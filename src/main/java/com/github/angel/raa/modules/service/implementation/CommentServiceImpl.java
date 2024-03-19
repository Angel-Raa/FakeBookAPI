package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.exception.CommentNotFoundExceptionHandler;
import com.github.angel.raa.modules.exception.PostNotFoundExceptionHandler;
import com.github.angel.raa.modules.persistence.modesl.Comment;
import com.github.angel.raa.modules.persistence.modesl.Post;
import com.github.angel.raa.modules.persistence.modesl.Users;
import com.github.angel.raa.modules.persistence.repository.CommentRepository;
import com.github.angel.raa.modules.persistence.repository.PostRepository;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import com.github.angel.raa.modules.service.intefaces.CommentService;
import com.github.angel.raa.modules.utils.DTO.CommentDTO;
import com.github.angel.raa.modules.utils.api.Message;
import com.github.angel.raa.modules.utils.api.Response;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
        return commentRepository.findAll()
                .stream()
                .map(it -> new CommentDTO(it.getId(), it.getContent(), it.getCreatedAt(), it.getUpdatedAt()))
                .collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public CommentDTO getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .stream()
                .map(it -> new CommentDTO(it.getId(), it.getContent(), it.getCreatedAt(), it.getUpdatedAt()))
                .findFirst()
                .orElseThrow(() -> new CommentNotFoundExceptionHandler(Message.COMMENT_NOT_FOUND_ID_POST_ID_USER_ID, 404));
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<CommentDTO> createComment(@NotNull CommentDTO commentDTO, Long postId, Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new PostNotFoundExceptionHandler(Message.USER_NOT_FOUND_ID, 404));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundExceptionHandler(Message.POST_NOT_FOUND_ID, 404));
        Comment comment = new Comment();
        comment.setContent(commentDTO.content());
        comment.setPost(post);
        comment.setUsers(users);
        commentRepository.save(comment);
        CommentDTO dto = mapCommentToDTO(comment);
        return new Response<>(Message.COMMENT_CREATE, HttpStatus.CREATED, true, LocalDateTime.now(), dto);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<CommentDTO> updateComment(@NotNull CommentDTO commentDTO, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundExceptionHandler(Message.COMMENT_NOT_FOUND_ID_POST_ID_USER_ID, 404));
        comment.setContent(commentDTO.content());
        commentRepository.save(comment);
        CommentDTO dto = mapCommentToDTO(comment);
        return new Response<>(Message.COMMENT_UPDATE, HttpStatus.OK, true, LocalDateTime.now(), dto);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<CommentDTO> deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundExceptionHandler(Message.COMMENT_NOT_FOUND_ID_POST_ID_USER_ID, 404));
        commentRepository.delete(comment);
        CommentDTO dto = mapCommentToDTO(comment);
        return new Response<>(Message.COMMENT_DELETE, HttpStatus.NO_CONTENT, true, LocalDateTime.now(), dto);
    }

    @Contract("_ -> new")
    private @NotNull CommentDTO mapCommentToDTO(@NotNull Comment comment){
        return new CommentDTO(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }
}
