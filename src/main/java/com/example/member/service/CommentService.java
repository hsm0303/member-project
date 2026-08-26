package com.example.member.service;

import com.example.member.domain.Comment;
import com.example.member.domain.Member;
import com.example.member.domain.Post;
import com.example.member.exception.MemberNotFoundException;
import com.example.member.exception.PostNotFoundException;
import com.example.member.repository.CommentRepository;
import com.example.member.repository.MemberRepository;
import com.example.member.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Comment createComment(String content, Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));

        Comment comment = new Comment(content, member, post);

        return commentRepository.save(comment);
    }

    public List<Comment> findCommentsByPostId(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
        return commentRepository.findByPostId(postId);
    }

    public void UpdateComment(Long commentId, String comment) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
        Comment comment = new Comment()
    }
}
