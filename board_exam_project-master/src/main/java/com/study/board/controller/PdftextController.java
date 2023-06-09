package com.study.board.controller;

import com.study.board.service.PdfService;
import org.apache.http.HttpResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PdftextController {

    @Autowired
    private PdfService pdfService;


    @GetMapping("/pdftext")
    public String pdftext() {
        Random random = new Random();
        PDDocument document = null;
        String path = "./pdf/test3.pdf"; // PDF file path
        String outputFilePath = "./pdf/test3.txt"; // output text file path

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
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Write JSON data to the connection's output stream
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter httpWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                httpWriter.write(jsonArray.toString());
                httpWriter.flush();
                httpWriter.close();

                // read response from server
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // request processed successfully
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String response = reader.readLine();
                    reader.close();
                    System.out.println("Server response: " + response);
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

        return "main";
    }

}
