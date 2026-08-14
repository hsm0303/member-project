package com.example.member.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity //아래 클래스가 데이터베이스 테이블과 연결되는 JPA 엔티티이다.
public class Member {

    @Id //id 필드가 테이블의 기본키이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID를 직접 안 만들고 데이터베이스가 자동으로 증가시켜 주게 하는 설정
    private Long id;

    private String name;
    private String email;

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    protected Member() { //JPA가 엔티티 객체를 만들 때 필요한 기본 생성자
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public void changeName(String name) {
        this.name = name;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
