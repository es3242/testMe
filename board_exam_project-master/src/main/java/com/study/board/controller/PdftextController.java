package com.study.board.controller;


import org.apache.http.HttpResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


//
@Controller
public class PdftextController {

    @GetMapping("/pdftext")
    public String pdftext() {
        Random random = new Random();
        PDDocument document = null;
        String path = "C:\\asdasd\\test3.pdf"; // PDF 파일 경로
        String outputFilePath = "C:\\asdasd\\test3.txt"; // 출력 텍스트 파일 경로

        try {
            document = PDDocument.load(new File(path)); // PDF 파일 로드
            PDFTextStripper stripper = new PDFTextStripper(); // PDF 텍스트 추출기
            int numPages = document.getNumberOfPages(); // 페이지 수 확인
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath)); // 텍스트 파일 작성기

            writer.write("Number of pages: " + numPages + "\n"); // 페이지 수를 파일에 작성

            for (int pageNum = 1; pageNum <= numPages; pageNum++) {
                stripper.setStartPage(pageNum); // 시작 페이지 설정
                stripper.setEndPage(pageNum); // 끝 페이지 설정
                String pageText = stripper.getText(document); // 페이지에서 텍스트 추출

                writer.write("Processed text from page " + pageNum + ":\n"); // 페이지 번호를 파일에 작성
                writer.write(pageText); // 처리된 텍스트를 파일에 작성

                writer.write("\nOriginal text:\n"); // 원본 텍스트를 파일에 작성
                writer.write(pageText); // 원본 텍스트를 파일에 작성
                writer.write("\n");

                // JSON 데이터 생성
                JSONObject json = new JSONObject();
                json.put("text", pageText);
                json.put("page", pageNum);

                try {
                    // HTTP 연결 설정
                    URL url = new URL("http://localhost:5000/endpoint");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);

                    // JSON 데이터를 연결의 출력 스트림에 작성
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter httpWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    httpWriter.write(json.toString());
                    httpWriter.flush();
                    httpWriter.close();

                    // 서버로부터 응답 읽기
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // 요청이 성공적으로 처리됨
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String response = reader.readLine();
                        reader.close();
                        System.out.println("Server response: " + response);
                    } else {
                        // 오류 응답 처리
                        System.out.println("Error response from server. Response Code: " + responseCode);
                    }

                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            writer.close();
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

        return "main";
    }



}
