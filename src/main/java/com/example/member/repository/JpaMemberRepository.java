package com.example.member.repository;

import com.example.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{
    private final SpringDataMemberRepository springDataMemberRepository;

    public JpaMemberRepository(SpringDataMemberRepository springDataMemberRepository) {
        this.springDataMemberRepository = springDataMemberRepository;
    }

    @Override
    public Member save(Member member) {
        return springDataMemberRepository.save(member);
    }

    @Override
    public List<Member> findAll() {
        return springDataMemberRepository.findAll();
    }

    @Override
    public List<Member> findAll(Sort sort) {
        return springDataMemberRepository.findAll(sort);
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return springDataMemberRepository.findAll(pageable);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return springDataMemberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return springDataMemberRepository.findByEmail(email);
    }

    @Override
    public void delete(Member member) {
        springDataMemberRepository.delete(member);
    }

    @Override
    public List<Member> findByNameContaining(String keyword) {
        return springDataMemberRepository.findByNameContaining(keyword);
    }
}
