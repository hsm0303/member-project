package com.example.member.repository;

import com.example.member.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
