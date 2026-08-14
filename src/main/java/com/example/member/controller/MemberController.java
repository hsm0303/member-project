package com.example.member.controller;

import com.example.member.domain.Member;
import com.example.member.dto.*;
import com.example.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public MemberResponse createMember(@Valid @RequestBody MemberCreateRequest request) {
        Member member = memberService.createMember(request.getName(), request.getEmail());

        return MemberResponse.from(member);
    }

    @GetMapping
    public List<MemberResponse> findAllMembers(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return memberService.findAllMembersSorted(sortBy, direction)
                .stream()
                .map(MemberResponse::from) //각 Member를 MemberResponse로 바꾸기
                .toList();
    }

    @GetMapping("/{id}")
    public MemberResponse findMember(@PathVariable Long id) {
        Member member = memberService.findMember(id);

        return MemberResponse.from(member);
    }

    @GetMapping("/search")
    public List<MemberResponse> searchMembers(@RequestParam String keyword) {
        return memberService.searchMemberByName(keyword)
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    @GetMapping("/sorted")
    public List<MemberResponse> findAllMemberSorted() {
        return memberService.findALlMembersOrderByDesc()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    @GetMapping("/page")
    public MemberPageResponse findAllMemberPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Member> memberPage = memberService.findAllMembersPaged(page, size, sortBy, direction);
        List<MemberResponse> members = memberPage.getContent() //현재 페이지에 들어있는 회원 목록
                .stream()
                .map(MemberResponse::from)
                .toList();

        return new MemberPageResponse(
                members,
                memberPage.getNumber(),
                memberPage.getSize(),
                memberPage.getTotalElements(),
                memberPage.getTotalPages()
        );
    }

    @GetMapping("/{id}/posts")
    public List<PostResponse> findPostsByMember(@PathVariable Long id) {
        return memberService.findPostByMemberId(id)
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    @PutMapping("/{id}")
    public MemberResponse updateMember(@Valid @PathVariable Long id, @RequestBody MemberUpdateRequest request) {
        Member member = memberService.updateMember(id, request.getName(), request.getEmail());

        return MemberResponse.from(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);

        return ResponseEntity.noContent().build();
    }
}
