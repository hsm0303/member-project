package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.domain.Post;
import com.example.member.exception.MemberNotFoundException;
import com.example.member.repository.MemberRepository;
import com.example.member.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //트랜잭션: 데이터베이스 작업을 하나의 안전한 작업 단위로 묶음, 성공하면 반영하고 실패하면 되돌리는 기능
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public MemberService(
            MemberRepository memberRepository,
            PostRepository postRepository
    ) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Member createMember(String name, String email) {
        validateName(name);
        validateEmail(email);
        validateDuplicateEmail(email);

        Member member = new Member(name, email);

        return memberRepository.save(member);
    }

    private void validateDuplicateEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
                });
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findALlMembersOrderByDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        return memberRepository.findAll(sort);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("회원을 찾을 수 없습니다.")
                );
    }

    public List<Post> findPostByMemberId(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new MemberNotFoundException("회원을 찾을 수 없습니다.")
                );

        return postRepository.findMemberById(memberId);
    }

    public List<Member> searchMemberByName(String keyword) {
        return memberRepository.findByNameContaining(keyword);
    }

    @Transactional
    public Member updateMember(Long id, String name, String email) {
        validateName(name);
        validateEmail(email);

        Member member = findMember(id);

        validateDuplicateEmailForUpdate(id, email);

        member.changeName(name);
        member.changeEmail(email);

        return member;
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = findMember(id);
        memberRepository.delete(member);
    }

    private void validateName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어 있을 수 없습니다.");
        }
    }

    private void validateEmail(String email) {
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 비어 있을 수 없습니다.");
        }

        if(!email.contains("@")) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }
    }

    private void validateDuplicateEmailForUpdate(Long id, String email) {
        memberRepository.findByEmail(email)
                .ifPresent(foundMember -> {
                    if(!foundMember.getId().equals(id)){
                        throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
                    }
                });
    }

    public List<Member> findAllMembersSorted(String sortBy, String direction) {
        Sort.Direction sortDirection;

        if(direction.equalsIgnoreCase("desc")){
            sortDirection = Sort.Direction.DESC;
        } else {
            sortDirection = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(sortDirection, sortBy);

        return memberRepository.findAll(sort);
    }

    public Page<Member> findAllMembersPaged(
            int page,
            int size,
            String sortBy,
            String direction
    ) { //반환형 List<Member>에서 Page<Member>로 교체
        Sort.Direction sortDirection;

        if(direction.equalsIgnoreCase("desc")) {
            sortDirection = Sort.Direction.DESC;
        } else {
            sortDirection = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(sortDirection, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        return memberRepository.findAll(pageable);
    }
}
