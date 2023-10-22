package com.study.board.controller;

import com.study.board.entity.Comment;
import com.study.board.entity.CommentReport;
import com.study.board.entity.Community;
import com.study.board.entity.CommunityReport;
import com.study.board.service.CommentReportService;
import com.study.board.service.CommentService;
import com.study.board.service.CommunityService;
import com.study.board.service.CommunityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private CommunityReportService communityReportService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentReportService commentReportService;

    @PostMapping("/community")
    public ResponseEntity<String> reportCommunity(@RequestParam("community_id") Long communityId,
                                                  @RequestParam("rContent") String rContent) {
        try {
            // Get the associated Community object
            Optional<Community> communityOptional = communityService.getCommunityById(communityId);
            if (!communityOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 Community를 찾을 수 없습니다.");
            }

            Community community = communityOptional.get();

            // Create a new CommunityReport instance
            CommunityReport communityReport = new CommunityReport();
            communityReport.setCommunity(community);
            communityReport.setRContent(rContent);
            communityReport.setCreateAt(new Date()); // Automatically set the createAt timestamp

            // Save the CommunityReport to the database
            communityReportService.createCommunityReport(communityReport);

            return ResponseEntity.ok("신고가 성공적으로 접수되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신고 생성에 실패했습니다.");
        }
    }

    //communityController의 create 메서드랑 비슷하게 내부 채우기 -> 이후 이거 가지고 comment 신고 메서드도 만들기
    @PostMapping("/comment")
    public ResponseEntity<String> reportComment(@RequestParam("comment_id") Long commentId,
                                                @RequestParam("rContent") String rContent) {
        try {
            Optional<Comment> commentOptional = commentService.getReportCommentById(commentId);
            if (!commentOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 Comment를 찾을 수 없습니다.");
            }

            Comment comment = commentOptional.get();

            CommentReport commentReport = new CommentReport();
            commentReport.setComment(comment);
            commentReport.setRContent(rContent);
            commentReport.setCreateAt(LocalDateTime.now());

            commentReportService.createCommentReport(commentReport);

            return ResponseEntity.ok("댓글 신고가 성공적으로 접수되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 신고 생성에 실패했습니다.");
        }
    }

}
