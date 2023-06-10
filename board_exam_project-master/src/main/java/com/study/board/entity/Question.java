package com.study.board.entity;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pdf_id", referencedColumnName = "pdf_id")
    private Pdf pdf;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "options")
    private String options;

    @Column(name = "answer")
    private String answer;

    @Column(name = "question_set", columnDefinition = "TEXT") // Specify column definition as TEXT
    private String questionSet;

    // 생성자, getter, setter 등은 생략

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    // Pdf 객체에 대한 접근자 메서드
    public Pdf getPdf() {
        return pdf;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestionSet() {
        return questionSet;
    }
}
