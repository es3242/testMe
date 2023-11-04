package com.study.board.controller;

import com.study.board.DTO.CommentDTO;
import com.study.board.entity.Comment;
import com.study.board.entity.Community;
import com.study.board.entity.User;
import com.study.board.repository.CommentRepository;
import com.study.board.service.CommentService;
import com.study.board.service.CommunityService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/create")
    public ResponseEntity<String> createComment(
            @RequestParam("user_id") Long userId,
            @RequestParam("community_id") Long communityId,
            @RequestParam("content") String content) {
        try {
            Comment newComment = commentService.createComment(userId, communityId, content);

            if (newComment != null) {
                return ResponseEntity.ok("Comment created successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create comment.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("id") Long id,
            @RequestParam(value = "content") String content) {
        try {
            Comment existingComment = commentService.getCommentById(id);

            if (existingComment == null) {
                return ResponseEntity.notFound().build();
            }

            // 댓글이 속한 게시글의 is_deleted를 확인 : 게시글이 존재하며 is_deleted가 false인(!false = true) 경우 -> 게시글이 삭제된 상태.
            Community community = existingComment.getCommunity();
            if (community != null && !community.getIsDeleted()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            existingComment.setContent(content);
            existingComment.setUpdatedAt(LocalDateTime.now());

            Comment updatedComment = commentService.updateComment(existingComment);

            // 업데이트된 Comment 엔터티를 CommentDTO로 변환
            CommentDTO updatedCommentDTO = new CommentDTO(updatedComment.getId(), updatedComment.getContent());

            return ResponseEntity.ok(updatedCommentDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id) {
        try {
            Comment existingComment = commentService.getCommentById(id);

            if (existingComment == null) {
                return ResponseEntity.notFound().build();
            }

            // 삭제된 댓글의 isDeleted 값을 변경하거나, DB에서 직접 삭제하는 로직을 구현해야 합니다.
            // 여기서는 isDeleted 값을 변경하는 예시를 보여드립니다.
            existingComment.setIsDeleted(false); // is_deleted(0 : 삭제, 1 : 삭제안된)를 false(0)로 설정

            Long communityId = existingComment.getCommunity().getId(); // 댓글이 참조하는 커뮤니티 ID 가져오기
            commentService.updateComment(existingComment); // 변경사항 저장

            // 해당 커뮤니티의 comment_number을 1 감소시키는 로직 추가
            communityService.decrementCommentNumber(communityId);

            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("댓글 삭제에 실패했습니다.");
        }
    }


    @GetMapping("/{community_id}/comments")
    public ResponseEntity<?> getCommentsByCommunityId(
            @PathVariable("community_id") long communityId) {

        Optional<Community> communityOptional = communityService.getCommunityById(communityId);

        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();

            if (community.getIsDeleted()) {
                List<Comment> comments = commentService.getActiveCommentsByCommunityId(communityId);

                if (!comments.isEmpty()) {
                    List<CommentDTO> commentDTOs = comments.stream()
                            .map(comment -> new CommentDTO(comment.getId(), comment.getContent()))
                            .collect(Collectors.toList());

                    return ResponseEntity.ok(commentDTOs);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다.");
        }
    }




}
