package com.study.board.service;

import com.study.board.entity.Pdf;
import com.study.board.entity.User;
import com.study.board.repository.PdfRepository;
import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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









}

