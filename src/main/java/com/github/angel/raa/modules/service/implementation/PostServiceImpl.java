package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.exception.PostNotFoundExceptionHandler;
import com.github.angel.raa.modules.persistence.modesl.Post;
import com.github.angel.raa.modules.persistence.modesl.Users;
import com.github.angel.raa.modules.persistence.repository.PostRepository;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import com.github.angel.raa.modules.service.intefaces.PostService;
import com.github.angel.raa.modules.utils.dto.PostDTO;
import com.github.angel.raa.modules.utils.dto.PostWithCommentsDTO;
import com.github.angel.raa.modules.utils.api.Message;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.jwt.JwtTokenUtils;
import lombok.NonNull;
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
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;
    private final JwtTokenUtils tokenUtils;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Set<PostDTO> getAllPosts() {
        return repository.findAll().stream()
                .map(it -> new PostDTO(it.getPostId(), it.getTitle(), it.getContent(), it.getPublishedAt(), it.getUpdatedAt()))
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public PostDTO getPost(@NonNull Long postId) {
        return repository.findById(postId).stream().findFirst()
                .map(it -> new PostDTO(it.getPostId(), it.getTitle(), it.getContent(), it.getPublishedAt(), it.getUpdatedAt()))
                .orElseThrow(() -> new PostNotFoundExceptionHandler(Message.POST_NOT_FOUND_ID, 404));
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
    @Transactional
    @Override
    public Response<PostDTO> createPost(PostDTO postDTO, String token) {
        String username = tokenUtils.getUsernameFromToke(token);
        Users users = userRepository.findByUsername(username).orElseThrow(() -> new PostNotFoundExceptionHandler(Message.USER_NOT_FOUND_ID, 404));
        Post post = mapDTOToPost(postDTO);
        post.setUsers(users);
        PostDTO dto = mapPostToDTO(post);
        repository.save(post);
        return new Response<>(Message.POST_SAVE, HttpStatus.CREATED, true, LocalDateTime.now(), dto);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<PostDTO> updatePost(PostDTO postDTO, Long postId) {
        Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundExceptionHandler(Message.POST_NOT_FOUND_ID, 404));
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        repository.save(post);
        PostDTO dto = mapPostToDTO(post);
        return new Response<>(Message.POST_UPDATE, HttpStatus.OK, true, LocalDateTime.now(), dto);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Response<PostDTO> deletePost(Long postId) {
        Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundExceptionHandler(Message.POST_NOT_FOUND_ID, 404));
        repository.delete(post);
        return new Response<>(Message.POST_DELETE, HttpStatus.NO_CONTENT, true, LocalDateTime.now());
    }

    @Contract("_ -> new")
    private @NotNull PostDTO mapPostToDTO(@NotNull Post post){
        return new PostDTO(post.getPostId(), post.getTitle(), post.getContent(), post.getPublishedAt(), post.getUpdatedAt());
    }
    @Contract("_ -> new")
    private @NotNull Post mapDTOToPost(@NotNull PostDTO postDTO){
        Post post = new Post();
        post.setPostId(postDTO.postId());
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        return post;
    }
}
