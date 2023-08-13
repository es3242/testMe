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

import java.util.List;
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

    /*@PostMapping("/create")
    public ResponseEntity<String> createComment(
            @RequestParam("user_id") Long userId,
            @RequestParam("community_id") Long communityId,
            @RequestParam("content") String content) {
        try {
            // 게시글 정보 저장
            Comment comment = new Comment();

            comment.setContent(content);

            User user = userService.getUserInfo(userId); // 실제로는 UserService를 사용하여 사용자 정보를 얻어와야 합니다.
            comment.setUser(user);

            Community community = communityService.getCommunityById(communityId)
                    .orElseThrow(() -> new IllegalArgumentException("Community with ID " + communityId + " not found."));
            comment.setCommunity(community);

            Comment savedComment = commentService.createComment(comment);
            return ResponseEntity.ok("Comment created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

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

    @GetMapping("/{community_id}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByCommunityId(
            @PathVariable("community_id") long communityId) {

        List<Comment> comments = commentRepository.findByCommunityId(communityId);

        if (!comments.isEmpty()) {
            List<CommentDTO> commentDTOs = comments.stream()
                    .map(comment -> new CommentDTO(comment.getId(), comment.getContent()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(commentDTOs); // 200 OK 상태의 JSON 응답 반환
        } else {
            return ResponseEntity.notFound().build(); // 해당 커뮤니티에 대한 댓글이 없는 경우 404 Not Found 상태 반환
        }
    }

}
