package com.study.board.controller;

import com.study.board.entity.Pdf;
import com.study.board.entity.User;
import com.study.board.repository.PdfRepository;
import com.study.board.repository.UserRepository;
import com.study.board.service.PdfService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class PdfUpController {
    @Autowired
    PdfService pdfService = new PdfService();

    @GetMapping("/pdfup")
    public String pdfloding() {
        return "kpdfuplode";
    }

    @PostMapping("/pdfup1")
    public String uploadPdf(@RequestParam("pdfFile") MultipartFile pdfFile, @RequestParam("user.id") int userId, RedirectAttributes redirectAttributes) {
        if (pdfFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a PDF file to upload");
            return "redirect:/";
        }

        try {
            // 파일 저장 위치 설정
            String uploadDir = "./pdf";

            // 파일 이름 설정
            String originalFileName = pdfFile.getOriginalFilename();
            String fileName = StringUtils.cleanPath(originalFileName);

            // 파일 경로 설정
            String fileExtension = "";
            int i = fileName.lastIndexOf('.');
            if (i >= 0) {
                fileExtension = fileName.substring(i + 1);
                fileName = fileName.substring(0, i);
            }
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            fileName = fileName + "_" + timestamp + "." + fileExtension;
            String filePath = Paths.get(uploadDir, fileName).toString();

            System.out.println(filePath);
            // 파일 저장
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(pdfFile.getBytes());
            stream.close();

            // PDF 엔티티 생성 및 저장
            User user = new User();
            user.setId((long) userId);
            LocalDateTime createdAt = LocalDateTime.now();
            Pdf pdf = new Pdf();
            pdf.setUser(user);
            pdf.setCreatedAt(createdAt);
            pdf.setConvertedAt(0);
            pdf.setFilePath(filePath);
            pdfService.savePdf(pdf);

            redirectAttributes.addFlashAttribute("message", "PDF file has been uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }










    @Autowired
    private PdfRepository pdfRepository;



    @GetMapping("/pdf")
    public String getPdfList(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        List<Pdf> pdfList = pdfService.getPdfListByUserId(userId);

        for (Pdf pdf : pdfList) {
            String filePath = pdf.getFilePath();
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            System.out.println(fileName);
            pdf.setFilePath(fileName); // 원본 pdfList의 각 원소의 filePath 수정
        }

        model.addAttribute("pdfList", pdfList);
        return "kpdflist";
    }


    @PostMapping("/pdf/view")
    public void viewPdf(@RequestParam String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=" + file.getName());

        Files.copy(file.toPath(), response.getOutputStream());
        response.getOutputStream().flush();
    }







}




