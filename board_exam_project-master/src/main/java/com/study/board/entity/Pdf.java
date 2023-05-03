package com.study.board.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pdf")
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PDF_id")
    private int pdfId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "converted_at")
    private int convertedAt;

    @Column(name = "file_path")
    private String filePath;;





    public int getPdfId() {
        return pdfId;
    }

    public void setPdfId(int pdfId) {
        this.pdfId = pdfId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getConvertedAt() {
        return convertedAt;
    }

    public void setConvertedAt(int convertedAt) {
        this.convertedAt = convertedAt;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
