package com.study.board.controller;

import com.study.board.entity.Community;
import com.study.board.entity.User;
import com.study.board.service.CommunityService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<Community> list() {
        return communityService.getAllCommunities();
    }

    @GetMapping("/search")
    public List<Community> search(@RequestParam("query") Long query) {
        return communityService.searchCommunities(query);
    }

    @GetMapping("/titleSearch") //제목 검색
    public ResponseEntity<List<Community>> search(@RequestParam("query") String query) {
        List<Community> searchResults = communityService.searchByTitleContaining(query);
        if (!searchResults.isEmpty()) {
            return ResponseEntity.ok(searchResults); // 200 OK 상태의 JSON 응답 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }

    @GetMapping("/contentSearch")
    public ResponseEntity<List<Community>> searchByContent(@RequestParam("query") String query) {
        List<Community> searchResults = communityService.searchByContentContaining(query);
        if (!searchResults.isEmpty()) {
            return ResponseEntity.ok(searchResults); // 200 OK 상태의 JSON 응답 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }

    @GetMapping("/add")
    public ResponseEntity<String> add() {
        return ResponseEntity.ok("자유게시판 폼이 제공됩니다."); // JSON 응답 반환
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Community> edit(@PathVariable("id") Integer id) {
        Optional<Community> communityOptional = communityService.getCommunityById(id);
        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();
            return ResponseEntity.ok(community); // 200 OK 상태의 JSON 응답 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }


    @PostMapping("/save/{id}")
    public ResponseEntity<Community> save(
            @PathVariable long id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content) {
        Optional<Community> existingCommunityOptional = communityService.getCommunityById(id);

        if (!existingCommunityOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Community existingCommunity = existingCommunityOptional.get();

        // 파일 업로드 처리
        if (file != null && !file.isEmpty()) {
            String uploadDir = "./communityImages/";
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + fileName);

            try {
                Files.write(filePath, file.getBytes());
                existingCommunity.setAddFile(filePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        // 업데이트된 내용으로 existingCommunity의 컨텐츠 업데이트
        if (title != null) {
            existingCommunity.setTitle(title);
        }
        if (content != null) {
            existingCommunity.setContent(content);
        }

        // 업데이트된 커뮤니티를 저장하는 서비스 호출
        Community savedCommunity = communityService.updateCommunity(id, existingCommunity);

        return ResponseEntity.ok(savedCommunity); // 업데이트된 JSON 응답과 함께 200 OK 반환
    }



    @PostMapping("/create")
    public ResponseEntity<String> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value =  "file", required = false) MultipartFile file,
            @RequestParam("cCategory") String cCategory,
            @RequestParam("sCategory") String sCategory,
            @RequestParam("user_id") Long userId) {
        try {
            // 게시글 정보 저장
            Community community = new Community();
            community.setTitle(title);
            community.setContent(content);
            community.setcCategory(cCategory);
            community.setsCategory(sCategory);

            // 새로 추가된 필드 초기화
            community.setCommentNumber(0);

            // 파일을 저장할 경로 설정 (예시로 C:/uploads 폴더에 저장)
            String uploadDir = "./communityImages/";

            if (file != null && !file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path filePath = Paths.get(uploadDir + fileName);

                Files.write(filePath, file.getBytes()); // IOException can be thrown here
                community.setAddFile(filePath.toString());
            }

            // 사용자 정보 설정 (예: User 객체를 얻어온다고 가정)
            User user = userService.getUserInfo(userId); // 실제로는 UserService를 사용하여 사용자 정보를 얻어와야 합니다.
            community.setUser(user);

            // 게시글 정보와 파일의 경로를 데이터베이스에 저장
            Community savedCommunity = communityService.createCommunity(community);

            return ResponseEntity.ok("Post created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create post.");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        communityService.deleteCommunity(id);
        return ResponseEntity.ok("포스트가 성공적으로 삭제되었습니다.");
    }


    @PostMapping("/increaseLikes/{id}")
    public ResponseEntity<Community> increaseLikes(@PathVariable("id") long id) {
        Optional<Community> communityOptional = communityService.getCommunityById(id);
        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();
            community.setLikes(community.getLikes() + 1); // likes 값을 1 증가시킴
            Community updatedCommunity = communityService.createCommunity(community);
            return ResponseEntity.ok(updatedCommunity); // 200 OK 상태의 JSON 응답 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }

}
