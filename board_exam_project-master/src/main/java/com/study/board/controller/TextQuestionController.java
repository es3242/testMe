package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.Pdf;
import com.study.board.entity.Question;
import com.study.board.entity.User;
import com.study.board.repository.PdfRepository;
import com.study.board.repository.QuestionRepository;
import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TextQuestionController {

    private final PdfRepository pdfRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public TextQuestionController(PdfRepository pdfRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.pdfRepository = pdfRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/display")
    public String displayText(Model model) throws IOException {
        // txt 파일 경로 설정
        String txtFilePath = "./pdf/test.txt";

        // txt 파일 읽기
        Path path = Paths.get(txtFilePath);
        String content = new String(Files.readAllBytes(path));

        // 페이지 수 계산
        int numberOfPages = content.split("Processed text from page \\d+:").length;

        // 페이지별 텍스트 분리
        String[] pageTexts = content.split("Processed text from page \\d+:");

        System.out.println(pageTexts);
        System.out.println(numberOfPages);

        // 모델에 데이터 추가
        model.addAttribute("pageTexts", pageTexts);
        model.addAttribute("numberOfPages", numberOfPages);

        return "pdf/createquestion";
    }

    @GetMapping(value = "/display2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> displayText() throws IOException {
        // txt 파일 경로 설정
        String txtFilePath = "./pdf/test.txt";

        // txt 파일 읽기
        Path path = Paths.get(txtFilePath);
        String content = new String(Files.readAllBytes(path));

        // 페이지 수 계산
        int numberOfPages = content.split("Processed text from page \\d+:").length;

        // 페이지별 텍스트 분리
        String[] pageTexts = content.split("Processed text from page \\d+:");

        System.out.println(pageTexts);
        System.out.println(numberOfPages);

        // 결과 반환을 위한 HashMap 생성
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("pageTexts", pageTexts);
        responseBody.put("numberOfPages", numberOfPages);

        // ResponseEntity 반환
        return ResponseEntity.ok(responseBody);
        // return result;
    }

    @PostMapping("/display")
    public String saveQuestions(@RequestParam("pdfId") Integer pdfId,
                                @RequestParam("user_id") Long userId,
                                @RequestParam("questionTexts") List<String> questionTexts,
                                @RequestParam("optionsList") List<String> optionsList,
                                @RequestParam("answers") List<String> answers,
                                HttpSession session) {

        // 사용자 정보 가져오기
        User user = userRepository.findById(userId).orElse(null);

        for (int i = 0; i < questionTexts.size(); i++) {
            // 문제, 보기, 정답을 이용하여 Question 객체 생성 및 저장
            Question question = new Question();

            // PDF 정보 설정
            Pdf pdf = pdfRepository.findById(pdfId).orElse(null);
            question.setPdf(pdf);

            question.setUser(user);
            question.setQuestionText(questionTexts.get(i));
            question.setOptions(optionsList.get(i));
            question.setAnswer(answers.get(i));
            question.setQuestionSet(""); // 이 부분은 필요 없으므로 비워둡니다

            questionRepository.save(question);
        }

        return "redirect:/";
    }

    @GetMapping("/display/{pdfId}/{userId}/questions")
    public String getSavedQuestions(@PathVariable("pdfId") Integer pdfId, @PathVariable("userId") Long userId, Model model) {
        List<Question> savedQuestions = questionRepository.findByPdfPdfIdAndUserId(pdfId, userId);
        model.addAttribute("questions", savedQuestions);
        return "pdf/questionshow";
    }


}
