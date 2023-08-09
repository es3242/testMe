package com.study.board.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment_report")
public class CommentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY) // Many comment reports can be associated with one comment
    @JoinColumn(name = "comment_id", referencedColumnName = "ID", nullable = false)
    private Comment comment;

    @Column(name = "r_content", nullable = false, length = 30)
    private String rContent;

    @Column(name = "create_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    // Constructors, getters, and setters...

    // Additional methods as needed...
}

