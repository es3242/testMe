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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "converted_at")
    private int convertedAt;

    @Column(name = "file_path")
    private String filePath;;



}
