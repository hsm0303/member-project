package com.example.member.repository;

import com.example.member.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataPostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMemberId(Long memberId);

    @Query("""
            select p
            from Post p
            join fetch p.member
            """)
    List<Post> findAllWithMember();
}
