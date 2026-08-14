package com.example.member.repository;

import com.example.member.domain.Member;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    List<Member> findAll();

    List<Member> findAll(Sort sort);

    Page<Member> findAll(Pageable pageable);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    List<Member> findByNameContaining(String keyword);

    void delete(Member member);
}
