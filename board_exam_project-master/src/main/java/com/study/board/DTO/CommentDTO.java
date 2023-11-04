package com.study.board.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;
    // 다른 필요한 필드들

    public CommentDTO(Long id, String content) {
        this.id = id;
        this.content = content;
        // 필요한 필드들 설정
    }

    // Getter, Setter 등 필요한 메서드들
}

