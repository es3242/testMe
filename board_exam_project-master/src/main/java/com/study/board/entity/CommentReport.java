package com.study.board.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment_report")
public class CommentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", referencedColumnName = "ID", nullable = false)
    private Comment comment;

    @Column(name = "r_content", nullable = false, length = 30)
    private String rContent;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getRContent() {
        return rContent;
    }

    public void setRContent(String rContent) {
        this.rContent = rContent;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
