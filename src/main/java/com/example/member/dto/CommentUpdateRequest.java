package com.example.member.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentUpdateRequest {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    public CommentUpdateRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
