package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.PostService;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.dto.PostDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    @PreAuthorize("permitAll")
    @GetMapping("/")
    public ResponseEntity<Set<PostDTO>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PreAuthorize("permitAll")
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "postId") @Min(1) Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }


    @PreAuthorize("hasAuthority('CREATE_POST') || hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Response<PostDTO>> createPost(@Valid @RequestBody PostDTO dto, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(postService.createPost(dto, token), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_POST') || hasRole('ROLE_ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<Response<PostDTO>> updatePost(@Valid @RequestBody PostDTO dto, @PathVariable(name = "postId") @Min(1) Long postId) {
        return ResponseEntity.ok(postService.updatePost(dto, postId));
    }

    @PreAuthorize("hasAuthority('DELETE_POST') || hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Response<PostDTO>> deletePost(@PathVariable(name = "postId") @Min(1) Long postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }


}
