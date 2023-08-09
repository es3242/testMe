package com.study.board.controller;

import com.study.board.entity.Comment;
import com.study.board.entity.Community;
import com.study.board.entity.User;
import com.study.board.service.CommentService;
import com.study.board.service.CommunityService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

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

}
