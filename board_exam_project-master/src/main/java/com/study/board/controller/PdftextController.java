package com.study.board.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.board.service.PdfService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class PdftextController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdftext")
    public String pdftext(Model model) {
        Random random = new Random();
        PDDocument document = null;
        String relativePath = ""; // Absolute path
        String path = "C:/asdasd/test3.pdf"; // PDF file path
        String outputFilePath = "C:/asdasd/test3.txt"; // output text file path

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

            try {
                // set up HTTP connection
                URL url = new URL("http://localhost:5000/endpoint");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // 인코딩 명시
                connection.setDoOutput(true);

                // Write JSON data to the connection's output stream
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter httpWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                httpWriter.write(jsonArray.toString());
                httpWriter.flush();
                httpWriter.close();

                // 로그로 JSON 데이터 확인
                System.out.println("Sent JSON data to Flask server:");
                System.out.println(jsonArray.toString());

                // read response from server
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // request processed successfully
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String response = reader.readLine();
                    reader.close();
                    System.out.println("Server response: " + response);

                    // Parse the response JSON
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
                    List<String> testList = (List<String>) responseMap.get("Test");
                    List<String> solutionsList = (List<String>) responseMap.get("Solutions");
                    int questionsAmount = (int) responseMap.get("Questions Amount");

                    // Add the lists and other data to the model
                    model.addAttribute("tests", testList);
                    model.addAttribute("solutions", solutionsList);
                    model.addAttribute("questionsAmount", questionsAmount);

                } else {
                    // handle error response
                    System.out.println("Error response from server. Response Code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "pdftext";
    }

    @GetMapping("/pdfviewjpg")
    public String showPdfViewerJpg() {
        return "pdfviewjpg";
    }

    @GetMapping("/pdfviewpdf")
    public String showPdfViewerPdf() {
        return "pdfviewpdf";
    }

    @GetMapping("/pdfauto")
    public String pdfauto(Model model) {
        PDDocument document = null;
        String path = "C:/asdasd/test3.pdf"; // PDF 파일 경로
        String outputFilePath = "C:/asdasd/test3.txt"; // 출력 텍스트 파일 경로

        try {
            document = PDDocument.load(new File(path)); // PDF 파일 로드
            PDFTextStripper stripper = new PDFTextStripper(); // PDF 텍스트 추출기
            int numPages = document.getNumberOfPages(); // 페이지 수 확인
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath)); // 텍스트 파일 작성기

            writer.write("페이지 수: " + numPages + "\n"); // 파일에 페이지 수 작성

            JSONArray jsonArray = new JSONArray(); // 모든 페이지를 위한 JSONArray 생성

            for (int pageNum = 1; pageNum <= numPages; pageNum++) {
                stripper.setStartPage(pageNum); // 시작 페이지 설정
                stripper.setEndPage(pageNum); // 종료 페이지 설정
                String pageText = stripper.getText(document); // 페이지에서 텍스트 추출

                writer.write("페이지 " + pageNum + "에서 처리된 텍스트:\n"); // 파일에 페이지 번호 작성
                writer.write(pageText); // 처리된 텍스트를 파일에 작성
                writer.write("\n");

                // 페이지용 JSON 객체 생성
                JSONObject pageJson = new JSONObject();
                pageJson.put("text", pageText);

                // JSON 객체를 JSONArray에 추가
                jsonArray.put(pageJson);
            }
            writer.close();

            try {
                // set up HTTP connection
                URL url = new URL("http://localhost:5000/endtest");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // 인코딩 명시
                connection.setDoOutput(true);

                // Write JSON data to the connection's output stream
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter httpWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                httpWriter.write(jsonArray.toString());
                httpWriter.flush();
                httpWriter.close();

                // 로그로 JSON 데이터 확인
                System.out.println("Sent JSON data to Flask server:");
                System.out.println(jsonArray.toString());

                // read response from server
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // request processed successfully
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String response = reader.readLine();
                    reader.close();
                    System.out.println("Server response: " + response);

                    // Parse the response JSON
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
                    List<String> testList = (List<String>) responseMap.get("Test");

                    // Add the lists and other data to the model
                    model.addAttribute("tests", testList);

                } else {
                    // handle error response
                    System.out.println("Error response from server. Response Code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "pdfauto";
    }


}
