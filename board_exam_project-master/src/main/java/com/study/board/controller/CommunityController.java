package com.study.board.controller;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    /*@GetMapping("/list")
    public List<Community> list() {
        return communityService.getAllCommunities();
    }//is_deleted 관계없이 모두 조회*/

    @GetMapping("/list") //is_deletd가 true인 community만 조회
    public List<Community> list() {
        return communityService.getDeletedCommunities();
    }


    /*@GetMapping("/search") //사용하지 않음
    public List<Community> search(@RequestParam("query") Long query) {
        return communityService.searchCommunities(query);
    }*/



    /*@GetMapping("/titleSearch") //제목 검색
    public ResponseEntity<List<Community>> searchByTitle(@RequestParam("query") String query) {
        List<Community> searchResults = communityService.searchByTitleContaining(query);
        if (!searchResults.isEmpty()) {
            return ResponseEntity.ok(searchResults); // 200 OK 상태의 JSON 응답 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }*/

    @GetMapping("/titleSearch") //띄어쓰기 인식 가능. 단 공백포함 최소 2글자여야함. //연속으로 검색하는 것이 힘들 수도 있음.
    public ResponseEntity<?> searchByTitle(@RequestParam("query") String query) {
        try {
            query = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 예외 처리
        }

        List<Community> searchResults = communityService.searchByTitleContaining(query);

        if (!searchResults.isEmpty()) {
            // 검색 결과가 있을 경우
            return ResponseEntity.ok(searchResults); // 200 OK 상태의 JSON 응답 반환
        } else {
            // 검색 결과가 없을 경우
            String message = "해당하는 게시글이 존재하지 않습니다";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(message)); // 404 Not Found 상태와 메시지 반환
        }
    }


    /*@GetMapping("/contentSearch") //내용으로 검색
    public ResponseEntity<List<Community>> searchByContent(@RequestParam("query") String query) {
        List<Community> searchResults = communityService.searchByContentContaining(query);
        if (!searchResults.isEmpty()) {
            return ResponseEntity.ok(searchResults); // 200 OK 상태의 JSON 응답 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }*/
    @GetMapping("/contentSearch") // 내용으로 검색, 최소 2글자(공백포함), 연속 검색은 불가능...
    public ResponseEntity<?> searchByContent(@RequestParam("query") String query) {
        try {
            query = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 예외 처리
        }

        List<Community> searchResults = communityService.searchByContentContaining(query);

        if (!searchResults.isEmpty()) {
            // 검색 결과가 있을 경우
            return ResponseEntity.ok(searchResults); // 200 OK 상태의 JSON 응답 반환
        } else {
            // 검색 결과가 없을 경우
            String message = "해당하는 게시글이 존재하지 않습니다";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(message)); // 404 Not Found 상태와 메시지 반환
        }
    }

    @GetMapping("/add")
    public ResponseEntity<String> add() {
        return ResponseEntity.ok("자유게시판 폼이 제공됩니다."); // JSON 응답 반환
    }


    @GetMapping("/edit/{id}")
    public ResponseEntity<Community> edit(@PathVariable("id") Long id) {
        Optional<Community> communityOptional = communityService.getCommunityById(id);

        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();

            if (community.getIsDeleted()) {
                return ResponseEntity.ok(community); // 200 OK 상태의 JSON 응답 반환
            } else {
                // 삭제된 게시글인 경우 404 Not Found 상태 반환
                return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
            }
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }



    /*@PostMapping("/save/{id}")
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
    }*/

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

        // 게시글이 삭제되었는지 확인
        if (!existingCommunity.getIsDeleted()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

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
    public ResponseEntity<String> deleteCommunity(@PathVariable("id") Long id) {
        try {
            Optional<Community> optionalCommunity = communityService.getCommunityById(id);
            if (!optionalCommunity.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Community existingCommunity = optionalCommunity.get();


            if (existingCommunity == null) {
                return ResponseEntity.notFound().build();
            }

            // 이미 삭제된 경우
            if (existingCommunity.getIsDeleted() == false) {
                return ResponseEntity.badRequest().body("이미 삭제된 게시글입니다.");
            }

            // 커뮤니티의 is_deleted 값을 false로 설정하여 삭제합니다.
            existingCommunity.setIsDeleted(false);

            // 변경사항을 저장합니다.
            communityService.updateCommunity(id, existingCommunity);

            return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("게시글 삭제에 실패했습니다.");
        }
    }

    //해야할 것(23.09.13)
    //게시글 삭제 잘 작동하는지 확인(확인완료)+삭제된 게시글 접근 금지되게 하기(완료)
    //조회(전체, 제목검색, 내용검색)에서 삭제된 게시글 조회 안되게 하기 -> 전체 완료, 제목, 내용 검색도 완료
    //게시글 수정(edit, save), 게시글 좋아요 모두 삭제 시 사용 불가능하게 막기. -> edit완료,save 완료
    //게시글이 삭제되면 해당 게시글을 참조하는 댓글에 접근할 수 없게 하기.(조회, 수정) -> 조회, 수정 둘 다 완료.
    //작성한 코드들을 html 페이지를 반환하는 형태로 바꾸기(json 코드와 이름 안 겹치게 해보기)


    
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

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewPost(@PathVariable("id") long id) {
        Optional<Community> communityOptional = communityService.getCommunityById(id);
        if (communityOptional.isPresent()) {
            Community community = communityOptional.get();
            if (community.getIsDeleted()) { // is_deleted가 true인 경우에만 조회 및 업데이트 진행
                community.setView(community.getView() + 1); // 조회수 증가
                communityService.updateCommunity(id, community); // 업데이트된 게시글 저장
                return ResponseEntity.ok(community); // 200 OK 상태의 JSON 응답 반환
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
            }
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 상태 반환
        }
    }

}
