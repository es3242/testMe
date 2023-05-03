package com.study.board.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
@Controller
public class PdftextController {


    @GetMapping("/pdftext")
    public String pdftext() {

        PDDocument document = null;
        String path = "C:\\Users\\skawl\\OneDrive\\바탕 화면\\성결\\ai\\01_인공지능개요.pdf";


        try {
            document = PDDocument.load(new File(path));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println(text);
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

