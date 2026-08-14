package com.example.member.dto;


import com.example.member.domain.Comment;

public class CommentResponse {

    private Long id;
    private String content;
    private Long memberId;
    private String memberName;
    private Long postId;

    public CommentResponse(Long id, String content, Long memberId, String memberName, Long postId) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
        this.postId = postId;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getMember().getId(),
                comment.getMember().getName(),
                comment.getPost().getId()
        );
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public Long getPostId() {
        return postId;
    }
}
