package com.study.board.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "community_report")
public class CommunityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY) // Many community reports can be associated with one community
    @JoinColumn(name = "community_id", referencedColumnName = "id", nullable = false)
    private Community community;

    @Column(name = "r_content", nullable = false, length = 30)
    private String rContent;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getRContent() {
        return rContent;
    }

    public void setRContent(String rContent) {
        this.rContent = rContent;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    // Additional methods as needed...
}

