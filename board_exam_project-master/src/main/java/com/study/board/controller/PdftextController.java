package com.study.board.controller;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.util.*;

@Controller
public class PdftextController {

    @GetMapping("/pdftext")
    public String pdftext() {
        Random random = new Random();
        PDDocument document = null;
        String path = "C:\\asdasd\\test3.pdf";
        String outputFilePath = "C:\\asdasd\\test3.txt";

        try {
            document = PDDocument.load(new File(path));
            PDFTextStripper stripper = new PDFTextStripper();
            int numPages = document.getNumberOfPages();
            Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            writer.write("Number of pages: " + numPages + "\n"); // write number of pages to file

            for (int pageNum = 1; pageNum <= numPages; pageNum++) {
                stripper.setStartPage(pageNum);
                stripper.setEndPage(pageNum);
                String pageText = stripper.getText(document);

                KomoranResult analyzeResultList = komoran.analyze(pageText);
                List<Token> tokenList = analyzeResultList.getTokenList();

                writer.write("Processed text from page " + pageNum + ":\n"); // write page number to file
                int numNouns = 0; // keep track of the number of nouns encountered
                Map<Integer, Token> nounPositions = new HashMap<>(); // keep track of the positions of nouns
                for (Token token : tokenList) {
                    if (token.getPos().startsWith("N")) { // check if token is a noun
                        numNouns++;
                        if (numNouns <= 3) { // only change the first 3 nouns encountered
                            nounPositions.put(token.getBeginIndex(), token);
                        } else {
                            // write the noun as is
                            writer.write(String.format("%s ", token.getMorph()));
                        }
                    } else {
                        // write non-nouns as is
                        writer.write(String.format("%s ", token.getMorph()));
                    }
                }
                writer.write("\nOriginal text:\n");
                for (int i = 0; i < pageText.length(); i++) {
                    if (nounPositions.containsKey(i)) {
                        writer.write(String.format("(%s)", "----")); // write "(----)" instead of the noun
                    } else {
                        writer.write(String.format("%s", pageText.charAt(i))); // write non-noun character
                    }
                }
                writer.write("\n");
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
