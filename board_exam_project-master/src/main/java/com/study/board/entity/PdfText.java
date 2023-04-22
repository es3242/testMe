package com.study.board.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pdftext")
public class PdfText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_id")
    private int textId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PDF_id")
    private Pdf pdf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // constructors, getters and setters
}
