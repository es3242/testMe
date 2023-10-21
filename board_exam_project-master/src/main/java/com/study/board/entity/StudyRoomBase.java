package com.study.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "study_room_base")
public class StudyRoomBase {

    @Id
    @Column(name = "study_room_name", nullable = false)
    private String studyRoomName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private User user;


    @Column(name = "questions")
    private String questions;

    public String getStudyRoomName() {
        return studyRoomName;
    }

    public void setStudyRoomName(String studyRoomName) {
        this.studyRoomName = studyRoomName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
