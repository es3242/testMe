package com.study.board.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "c_category", nullable = false, length = 10) //게시판 카테고리
    private String cCategory;

    @Column(name = "s_category", nullable = false, length = 10) //과목(태그) 카테고리
    private String sCategory;

    @Column(name = "likes")
    private int likes;

    @Column(name = "looking")
    private int looking;

    @Column(name = "add_file", length = 255)
    private String addFile;

    @Column(name = "report", nullable = false, columnDefinition = "int default 0")
    private int report;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "int default 1")
    private int isDeleted;

    @Column(name = "comment_number", nullable = false)
    private int commentNumber;

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getcCategory() { //게시판 카테고리 조회
        return cCategory;
    }

    public void setcCategory(String cCategory) { //게시판 카테고리 설정
        this.cCategory = cCategory;
    }

    public String getsCategory() { //과목 카테고리 조회
        return sCategory;
    }

    public void setsCategory(String sCategory) { //과목 카테고리 설정
        this.sCategory = sCategory;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLooking() {
        return looking;
    }

    public void setLooking(int looking) {
        this.looking = looking;
    }

    public String getAddFile() {
        return addFile;
    }

    public void setAddFile(String addFile) {
        this.addFile = addFile;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }
}
