package com.example.member.controller;

import com.example.member.domain.Comment;
import com.example.member.dto.CommentCreateRequest;
import com.example.member.dto.CommentResponse;
import com.example.member.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponse createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentCreateRequest request
    ){
        Comment comment = commentService.createComment(
                request.getContent(),
                request.getMemberId(),
                postId
        );

        return CommentResponse.from(comment);
    }
}
