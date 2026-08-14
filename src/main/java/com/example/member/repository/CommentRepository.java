package com.example.member.repository;

import com.example.member.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    List<Comment> findAll();

    Optional<Comment> findById(Long id);

    void deleteById(Long id);

    List<Comment> findByPostId(Long postId);
}
