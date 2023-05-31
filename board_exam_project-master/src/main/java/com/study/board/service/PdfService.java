package com.study.board.service;

import com.study.board.entity.Pdf;
import com.study.board.entity.User;
import com.study.board.repository.PdfRepository;
import com.study.board.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    PdfRepository pdfRepository;

    @Autowired
    private UserRepository userRepository;

    public Pdf savePdf(Pdf pdf) {
        return pdfRepository.save(pdf);
    }

    public List<Pdf> getPdfListByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return pdfRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public void sendInfoToServer(JSONObject json) {
        try {
            // 외부 서버의 주소나 엔드포인트에 맞게 URL을 수정합니다
            URL url = new URL("http://external-server.com/endpoint");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter httpWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            httpWriter.write(json.toString());
            httpWriter.flush();
            httpWriter.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();
                reader.close();
                System.out.println("Server response: " + response);
            } else {
                System.out.println("Error response from server. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }









}

