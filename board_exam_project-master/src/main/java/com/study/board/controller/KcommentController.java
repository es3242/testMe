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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Kcomment")
public class KcommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/create")
    public String showCreateCommentForm(Model model) {
        // 필요한 속성을 모델에 추가하십시오.
        return "community/commentForm";
    }

    @PostMapping("/create")
    public String createComment(
            @RequestParam("user_id") Long userId,
            @RequestParam("community_id") Long communityId,
            @RequestParam("content") String content,
            Model model) {
        try {
            Comment newComment = commentService.createComment(userId, communityId, content);

            if (newComment != null) {
                model.addAttribute("message", "댓글이 성공적으로 작성되었습니다.");
            } else {
                model.addAttribute("error", "댓글 작성에 실패했습니다.");
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Kcommunity/view/"+communityId;
    }


    /*@GetMapping("/comments/{community_id}")
    public String getCommentsByCommunityId(
            @PathVariable("community_id") long communityId, Model model) {

        Optional<Community> communityOptional = communityService.getCommunityById(communityId);

        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();

            if (!community.getIsDeleted()) {
                model.addAttribute("error", "해당 커뮤니티가 존재하지 않습니다.");
                return "redirect:/Kcommunity/list"; // 혹은 원하는 URL로 수정
            }

            List<Comment> comments = commentService.getActiveCommentsByCommunityId(communityId);

            if (!comments.isEmpty()) {
                List<CommentDTO> commentDTOs = comments.stream()
                        .map(comment -> new CommentDTO(comment.getId(), comment.getContent(), comment.getUser(), comment.getCreatedAt()))
                        .collect(Collectors.toList());
                model.addAttribute("comments", commentDTOs);
            } else {
                model.addAttribute("error", "댓글이 없습니다.");
            }
        } else {
            model.addAttribute("error", "해당 커뮤니티가 존재하지 않습니다.");
            return "redirect:/Kcommunity/list"; // 혹은 원하는 URL로 수정
        }
        return "community/comments"; // comments.html을 반환합니다.
    }*/

}
