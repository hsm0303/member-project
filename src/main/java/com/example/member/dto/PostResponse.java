package com.example.member.dto;

import com.example.member.domain.Post;

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String memberName;

    public PostResponse(Long id, String title, String content, Long memberId, String memberName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getId(),
                post.getMember().getName()
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
}
