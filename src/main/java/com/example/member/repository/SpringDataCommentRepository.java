package com.example.member.repository;

import com.example.member.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCommentRepository extends JpaRepository<Comment, Long> {
}
