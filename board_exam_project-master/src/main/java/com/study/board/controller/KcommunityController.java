package com.study.board.controller;

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


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("message", "자유게시판 폼이 제공됩니다.");
        return "community/form";
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

    @GetMapping("/view/{id}") //게시글 개별 조회
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
    }

    @PostMapping("/increaseLikes/{id}")
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
    }


}
