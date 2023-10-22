package com.study.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "study_room_details")
public class StudyRoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_room_id", nullable = false)
    private int studyRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_room_name", referencedColumnName = "study_room_name", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private StudyRoomBase studyRoomBase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private User user;

    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "user_class", nullable = false)
    private int userClass;




    public int getStudyRoomId() {
        return studyRoomId;
    }

    public void setStudyRoomId(int studyRoomId) {
        this.studyRoomId = studyRoomId;
    }

    public StudyRoomBase getStudyRoomBase() {
        return studyRoomBase;
    }

    public void setStudyRoomBase(StudyRoomBase studyRoomBase) {
        this.studyRoomBase = studyRoomBase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserClass() {
        return userClass;
    }

    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }
}
