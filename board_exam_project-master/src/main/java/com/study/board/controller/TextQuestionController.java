package com.study.board.controller;

import com.study.board.entity.Pdf;
import com.study.board.entity.Question;
import com.study.board.entity.User;
import com.study.board.repository.PdfRepository;
import com.study.board.repository.QuestionRepository;
import com.study.board.repository.UserRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

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

    @PostMapping("/pdf/display")
    public String displayText(@RequestParam("filePath") String filePath,@RequestParam("pdfId") Integer pdfId, Model model) throws IOException {
        // pdf 경로를 입력 받아 불필요한 정보를 제거
        String modifiedFilePath = filePath.replace(".\\pdf\\", "").replaceAll("\\.pdf$", "");

        //
        PDDocument document = null;
        String path = "./pdf/" + filePath; // PDF file path
        String outputFilePath = modifiedFilePath+".txt";//"C:/asdasd/"+modifiedFilePath+".txt"; // output text file path

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
    public String getSavedQuestions(@PathVariable("pdfId") Integer pdfId, @PathVariable("userId") Long userId, Model model) {
        List<Question> savedQuestions = questionRepository.findByPdfPdfIdAndUserId(pdfId, userId);
        model.addAttribute("questions", savedQuestions);
        return "pdf/questionshow";
    }
}
