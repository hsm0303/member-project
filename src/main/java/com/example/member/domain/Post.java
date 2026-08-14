package com.example.member.domain;

import jakarta.persistence.*;

//엔티티란? 데이터베이스에 저장하고 JPA가 관리하는 Java 객체

@Entity //JPA에게 엔티티라고 알려주는 거
public class Post {

    @Id //이 필드가 이 엔티티의 기본 키(primary key)라고 알려주는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동 생성(DB의 자동 증가 기능을 사용해서 ID를 만들어라)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Post(){} //JPA가 객체를 만들기 위해서 필요

    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member; //Post -> Member 연결

        member.getPosts().add(this); //Member -> Post 연결
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

    public Member getMember() {
        return member;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
