package com.example.member.controller;

import com.example.member.domain.Post;
import com.example.member.dto.PostCreateRequest;
import com.example.member.dto.PostResponse;
import com.example.member.dto.PostUpdateRequest;
import com.example.member.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponse createPost(@Valid @RequestBody PostCreateRequest request) {
        Post post = postService.createPost(
                request.getTitle(),
                request.getContent(),
                request.getMemberId()
        );

        return PostResponse.from(post);
    }

    @GetMapping
    public List<PostResponse> findAllPosts() {
        return postService.findAllPosts()
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public PostResponse findPost(@PathVariable Long id) {
        Post post = postService.findPost(id);

        return PostResponse.from(post);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Long id,
                                   @Valid @RequestBody PostUpdateRequest request
    ) {
        Post post = postService.updatePost(
                id,
                request.getTitle(),
                request.getContent()
        );

        return PostResponse.from(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
