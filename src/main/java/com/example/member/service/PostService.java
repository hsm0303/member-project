package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.domain.Post;
import com.example.member.exception.MemberNotFoundException;
import com.example.member.exception.PostNotFoundException;
import com.example.member.repository.MemberRepository;
import com.example.member.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Post createPost(String title, String content, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다"));

        Post post = new Post(title, content, member);

        return postRepository.save(post);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAllWithMember();
    }

    public Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
    }

    @Transactional
    public Post updatePost(Long id, String title, String content) {
        Post post = findPost(id);

        post.changeTitle(title);
        post.changeContent(content);

        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = findPost(id);

        postRepository.delete(post);
    }
}
