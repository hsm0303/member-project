package com.example.member.repository;

import com.example.member.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCommentRepository implements CommentRepository {

    private final SpringDataCommentRepository springDataCommentRepository;

    public JpaCommentRepository(SpringDataCommentRepository springDataCommentRepository) {
        this.springDataCommentRepository = springDataCommentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return springDataCommentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return springDataCommentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return springDataCommentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        springDataCommentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return springDataCommentRepository.findByPostId(postId);
    }
}
