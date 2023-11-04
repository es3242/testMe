// CommentDTO.java
package com.study.board.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.board.entity.User;

import java.time.LocalDateTime;

public class CommentDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    private User user;

    private LocalDateTime createAt; // LocalDateTime 필드 추가

    public CommentDTO(Long id, String content, User user, LocalDateTime createAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.createAt = createAt;
    }

    // Getter 및 Setter 메서드
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreatedAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
