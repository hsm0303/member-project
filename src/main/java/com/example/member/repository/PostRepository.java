package com.example.member.repository;

import com.example.member.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    List<Post> findAll();

    Optional<Post> findById(Long id);

    List<Post> findMemberById(Long memberId);

    List<Post> findAllWithMember();

    void delete(Post post);
}
