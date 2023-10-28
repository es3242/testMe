package com.study.board.controller;

import com.study.board.entity.Comment;
import com.study.board.entity.Community;
import com.study.board.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.study.board.entity.Community;
import com.study.board.entity.User;
import com.study.board.repository.CommentRepository;
import com.study.board.service.CommentService;
import com.study.board.service.CommunityService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/Kcommunity")
public class KcommunityController {


    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/list")
    public String list(Model model) {
        List<Community> communities = communityService.getDeletedCommunities();

        // Community 객체 내의 createAt 필드를 기준으로 리스트를 정렬합니다.
        Collections.sort(communities, Comparator.comparing(Community::getCreateAt).reversed());

        // 여기에서 c_category와 s_category 정보를 가져와서 Community 객체에 설정
        for (Community community : communities) {
            // 가져온 게시판 유형 정보를 설정합니다.
            String cCategory = community.getcCategory();
            // 가져온 과목 유형 정보를 설정합니다.
            String sCategory = community.getsCategory();

            community.setcCategory(cCategory);
            community.setsCategory(sCategory);
        }

        model.addAttribute("communities", communities);
        return "community/list";
    }

    @GetMapping("/titleSearch")
    public String searchByTitle(@RequestParam("query") String query, Model model) {
        try {
            query = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 예외 처리
        }

        List<Community> searchResults = communityService.searchByTitleContaining(query);

        // 결과를 최신순으로 정렬
        searchResults.sort(Comparator.comparing(Community::getCreateAt).reversed());

        if (!searchResults.isEmpty()) {
            model.addAttribute("searchResults", searchResults);
        } else {
            String errorMessage = "해당하는 게시글이 존재하지 않습니다";
            model.addAttribute("errorMessage", errorMessage);
        }
        return "community/titleSearchResults";
    }

    @GetMapping("/contentSearch")
    public String searchByContent(@RequestParam("query") String query, Model model) {
        try {
            query = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 예외 처리
        }

        List<Community> searchResults = communityService.searchByContentContaining(query);

        if (!searchResults.isEmpty()) {
            // 최신 작성순으로 정렬
            Collections.sort(searchResults, Comparator.comparing(Community::getCreateAt).reversed());
            model.addAttribute("searchResults", searchResults);
        } else {
            model.addAttribute("errorMessage", "해당하는 게시글이 존재하지 않습니다");
        }

        return "community/contentSearchResults"; // 검색 결과를 표시하는 Thymeleaf 템플릿의 이름
    }

    /*@GetMapping("/view/{id}") //게시글 개별 조회
    public String viewPost(@PathVariable("id") long id, Model model) {
        Optional<Community> communityOptional = communityService.getCommunityById(id);
        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();
            if (community.getIsDeleted()) { // is_deleted가 true인 경우에만 조회 및 업데이트 진행
                community.setView(community.getView() + 1); // 조회수 증가
                communityService.updateCommunity(id, community); // 업데이트된 게시글 저장
                model.addAttribute("community", community);
                return "community/view"; // HTML 템플릿의 이름
            } else {
                return "redirect:/error404"; // 404 에러가 발생했을 때 안내창을 나타내는 URL
            }
        } else {
            return "redirect:/error404"; // 404 에러가 발생했을 때 안내창을 나타내는 URL
        }
    }*/
    @GetMapping("/view/{id}") //게시글 개별 조회
    public String viewPost(@PathVariable("id") long id, Model model) {
        Optional<Community> communityOptional = communityService.getCommunityById(id);
        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();
            if (community.getIsDeleted()) { // is_deleted가 true인 경우에만 조회 및 업데이트 진행
                community.setView(community.getView() + 1); // 조회수 증가
                communityService.updateCommunity(id, community); // 업데이트된 게시글 저장

                // 아래의 코드를 사용하여 comment 데이터를 가져올 수 있습니다.
                List<Comment> comments = commentService.getActiveCommentsByCommunityId(id); // 해당 게시글의 댓글 가져오기
                model.addAttribute("community", community);
                model.addAttribute("comments", comments); // 댓글 목록을 모델에 추가

                return "community/view"; // HTML 템플릿의 이름
            } else {
                return "redirect:/error404"; // 404 에러가 발생했을 때 안내창을 나타내는 URL
            }
        } else {
            return "redirect:/error404"; // 404 에러가 발생했을 때 안내창을 나타내는 URL
        }
    }


    /*@PostMapping("/increaseLikes/{id}")
    public String increaseLikes(@PathVariable("id") long id, Model model) {
        Optional<Community> communityOptional = communityService.getCommunityById(id);
        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();
            community.setLikes(community.getLikes() + 1); // likes 값을 1 증가시킴
            communityService.updateCommunity(id, community); // 업데이트된 커뮤니티 정보를 저장

            // 업데이트된 커뮤니티 정보를 모델에 추가하고, 해당 커뮤니티의 뷰로 이동합니다.
            model.addAttribute("community", community);
            return "redirect:/Kcommunity/view/" + id;
        } else {
            // 해당 ID에 대한 커뮤니티가 없는 경우에 대한 처리
            return "error"; // 적절한 에러 뷰 이름을 반환합니다.
        }
    }*/

    @GetMapping("/form")
    public String showCommunityForm(Model model) {
        model.addAttribute("community", new Community());
        return "community/form";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Community community, Model model) {
        try {
            // 새로 추가된 필드 초기화
            community.setCommentNumber(0);

            community.setcCategory(community.getcCategory());
            community.setsCategory(community.getsCategory());


            // 파일을 저장할 경로 설정 (예시로 C:/uploads 폴더에 저장)
            /*String uploadDir = "./communityImages/";

            if (file != null && !file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path filePath = Paths.get(uploadDir + fileName);

                Files.write(filePath, file.getBytes()); // IOException can be thrown here
                community.setAddFile(filePath.toString());
            }*/

            // 사용자 정보 설정 (예: User 객체를 얻어온다고 가정)
            // User user = userService.getUserInfo(userId); // 실제로는 UserService를 사용하여 사용자 정보를 얻어와야 합니다.
            // community.setUser(user);

            // 게시글 정보와 파일의 경로를 데이터베이스에 저장
            Community savedCommunity = communityService.createCommunity(community);

            // 성공 메시지와 함께 community/form 페이지 반환
            model.addAttribute("successMessage", "Post created successfully.");
            return "redirect:/Kcommunity/list";
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 메시지와 함께 community/form 페이지 반환
            model.addAttribute("errorMessage", "Failed to create post.");
            return "community/list";
        }
    }





}
