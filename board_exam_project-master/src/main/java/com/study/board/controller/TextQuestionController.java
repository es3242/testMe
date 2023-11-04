package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.Pdf;
import com.study.board.entity.Question;
import com.study.board.entity.User;
import com.study.board.repository.PdfRepository;
import com.study.board.repository.QuestionRepository;
import com.study.board.repository.UserRepository;
import com.study.board.service.UserService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map;

@Controller
public class TextQuestionController {

    private final PdfRepository pdfRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public TextQuestionController(PdfRepository pdfRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.pdfRepository = pdfRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/pdf/display")
    public String displayText(@RequestParam("filePath") String filePath,@RequestParam("pdfId") Integer pdfId, Model model, HttpSession session) throws IOException {
        Object obj = session.getAttribute("user");
        System.out.println(obj);
        if (obj == null) {
            return "user/mypage";
        } else {
            Long userId = Long.parseLong(obj.toString());
            System.out.println(userId);

            User user = userService.getUserInfo(userId);
            System.out.println(user);

            model.addAttribute("user", user);

            // pdf 경로를 입력 받아 불필요한 정보를 제거
            String modifiedFilePath = filePath.replace(".\\pdf\\", "").replaceAll("\\.pdf$", "");

        System.out.println(pageTexts);
        System.out.println(numberOfPages);

        // 모델에 데이터 추가
        model.addAttribute("pageTexts", pageTexts);
        model.addAttribute("numberOfPages", numberOfPages);

            try {
                document = PDDocument.load(new File(path)); // load PDF file
                PDFTextStripper stripper = new PDFTextStripper(); // PDF text extractor
                int numPages = document.getNumberOfPages(); // check the number of pages
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath)); // text file writer

                writer.write("Number of pages: " + numPages + "\n"); // write page count to file

                JSONArray jsonArray = new JSONArray(); // create JSONArray for all pages

                for (int pageNum = 1; pageNum <= numPages; pageNum++) {
                    stripper.setStartPage(pageNum); // set start page
                    stripper.setEndPage(pageNum); // set end page
                    String pageText = stripper.getText(document); // extract text from page

                    writer.write("Processed text from page " + pageNum + ":\n"); // write the page numbers to the file
                    writer.write(pageText); // Write the processed text to a file
                    writer.write("\nOriginal text:\n"); // write original text to file
                    writer.write(pageText); // write original text to file
                    writer.write("\n");

                    // create JSON object for the page
                    JSONObject pageJson = new JSONObject();
                    pageJson.put("text", pageText);

                    // Add the JSON object to the JSONArray
                    jsonArray.put(pageJson);
                }
                writer.close();

            }catch (IOException e) {
                e.printStackTrace();
            }
            //


            // txt 파일 읽기
            Path pathread= Paths.get(outputFilePath);
            String content = new String(Files.readAllBytes(pathread));

            // 페이지 수 계산
            int numberOfPages = content.split("Processed text from page \\d+:").length;

            // 페이지별 텍스트 분리
            String[] pageTexts = content.split("Processed text from page \\d+:");

            // 모델에 데이터 추가
            model.addAttribute("pageTexts", pageTexts);
            model.addAttribute("numberOfPages", numberOfPages);

            model.addAttribute("pdfname", modifiedFilePath);
            model.addAttribute("pdfId", pdfId);
            return "pdf/createquestion";
        }
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

            questionRepository.save(question);
        }

        return "redirect:/display/" + pdfId + "/" + userId + "/questions";
    }


    @GetMapping("/display/{pdfId}/{userId}/questions")
    public String getSavedQuestions(@PathVariable("pdfId") Integer pdfId, @PathVariable("userId") Long userId, Model model, HttpSession session) {
        Object obj = session.getAttribute("user");
        System.out.println(obj);
        if (obj == null) {
            return "user/mypage";
        } else {
            // Long userId = Long.parseLong(obj.toString());
            System.out.println(userId);

            User user = userService.getUserInfo(userId);
            System.out.println(user);

            model.addAttribute("user", user);

            List<Question> savedQuestions = questionRepository.findByPdfPdfIdAndUserId(pdfId, userId);
            model.addAttribute("questions", savedQuestions);
            return "pdf/questionshow";
        }
    }
}
