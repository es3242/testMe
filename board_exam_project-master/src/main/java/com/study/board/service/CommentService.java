package com.study.board.service;

import com.study.board.entity.Comment;
import com.study.board.entity.Community;
import com.study.board.entity.User;
import com.study.board.repository.CommentRepository;
import com.study.board.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommunityService communityService;

    public Comment createComment(Long userId, Long communityId, String content) {
        User user = userService.getUserInfo(userId);

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("Community with ID " + communityId + " not found."));

        Comment newComment = new Comment();
        newComment.setUser(user);
        newComment.setCommunity(community); // 댓글과 게시글 연결
        newComment.setContent(content);
        newComment.setReport(0); // 레포트 초기값
        newComment.setIsDeleted(true); // 삭제 상태 초기값
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setUpdatedAt(LocalDateTime.now());

        // 댓글 생성 후 community의 comment_number 증가
        communityService.incrementCommentNumber(communityId);


        return commentRepository.save(newComment);
    }

    // 댓글 수정
    public Comment updateComment(Comment comment) {
        // 기존 댓글 엔터티를 받아서 업데이트합니다.
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    // 댓글 ID로 댓글 조회(수정전, is_deleted 구분X)
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    //게시글 ID를 참조하며 is_deleted가 1(true)인 댓글 조회
    public List<Comment> getActiveCommentsByCommunityId(Long communityId) {
        return commentRepository.findByCommunityIdAndIsDeleted(communityId, true);
    }

    //댓글 신고를 위한 코드
    public Optional<Comment> getReportCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }



}



