package com.example.member.dto;

import com.example.member.domain.Member;

public class MemberResponse {

    private Long id;
    private String name;
    private String email;

    public MemberResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static MemberResponse from(Member member) { //Member 객체를 받아서 MemberResponse 객체로 바꾸는 메서드
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
