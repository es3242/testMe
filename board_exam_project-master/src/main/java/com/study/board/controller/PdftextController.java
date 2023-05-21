package com.study.board.controller;


import org.apache.http.HttpResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

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
        PDDocument document = null;
        String path = "C:\\asdasd\\test3.pdf";
        String outputFilePath = "C:\\asdasd\\test3.txt";

        try {
            document = PDDocument.load(new File(path));
            PDFTextStripper stripper = new PDFTextStripper();
            int numPages = document.getNumberOfPages();

            // Create HttpClient and HttpPost objects
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost("http://localhost:5000/endpoint");
            httpPost.setHeader("Content-type", "application/json");

            for (int pageNum = 1; pageNum <= numPages; pageNum++) {
                stripper.setStartPage(pageNum);
                stripper.setEndPage(pageNum);
                String pageText = stripper.getText(document);

                // Create JSONObject to hold extracted text
                JSONObject json = null;
                try {
                    json = new JSONObject();
                    json.put("page", pageNum);
                    json.put("text", pageText);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "error";
                }

                // Convert JSONObject to string
                String jsonString = json.toString();
                System.out.println(jsonString);
                // Set JSON string as entity of HttpPost request
                StringEntity stringEntity = new StringEntity(jsonString);
                httpPost.setEntity(stringEntity);

                // Execute HttpPost request
                HttpResponse response = httpClient.execute(httpPost);

                // Check the response code
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 300) {
                    System.out.println("HTTP POST request for page " + pageNum + " succeeded with status code " + statusCode);
                } else {
                    System.out.println("HTTP POST request for page " + pageNum + " failed with status code " + statusCode);
                }
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

        return "main";
    }


}
