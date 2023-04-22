package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PdfUpController {

    @GetMapping("/pdfup")
    public String pdfloding() {
        return "pdf/pdfup";
    }


    @PostMapping("/pdfup1")
    @ResponseBody
    public String uploadPdf(@RequestParam("pdfFile") MultipartFile file) throws IOException {

        // Set the directory path where the PDF file will be saved.
        String uploadDir = "C:\\asdasd";


        // Create the directory if it doesn't exist.
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Save the PDF file to the upload directory.
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
        Files.write(path, bytes);

        // Save the file path to the database.
        String filePath = path.toString();
        // Code to save the filePath to the database.
        System.out.println("File path: " + filePath);

        // Return a success message to the client.
        String message = "File uploaded successfully!";
        System.out.println(message);
        return "File uploaded successfully!";
    }

}
