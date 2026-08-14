package com.example.member.repository;

import com.example.member.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPostRepository implements PostRepository{

    private final SpringDataPostRepository springDataPostRepository;

    public JpaPostRepository(SpringDataPostRepository springDataPostRepository) {
        this.springDataPostRepository = springDataPostRepository;
    }

    @Override
    public Post save(Post post) {
        return springDataPostRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return springDataPostRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return springDataPostRepository.findById(id);
    }

    @Override
    public List<Post> findMemberById(Long memberId) {
        return springDataPostRepository.findByMemberId(memberId);
    }

    @Override
    public List<Post> findAllWithMember() {
        return springDataPostRepository.findAllWithMember();
    }

    @Override
    public void delete(Post post) {
        springDataPostRepository.delete(post);
    }
}
