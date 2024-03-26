package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.PostService;
import com.github.angel.raa.modules.utils.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @PreAuthorize("permitAll")
    @GetMapping(value = {"/", "/all"})
    public ResponseEntity<Set<PostDTO>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }



}
