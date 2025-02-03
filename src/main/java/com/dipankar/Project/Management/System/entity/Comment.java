package com.dipankar.Project.Management.System.entity;

import com.dipankar.Project.Management.System.repo.UserRepo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    private LocalDateTime createDateTime;
    @ManyToOne
    private User user;
    @ManyToOne
    private Issue issue;

    public Comment(){}

    public Comment(Long id, String content, LocalDateTime createDateTime, User user, Issue issue) {
        this.id = id;
        this.content = content;
        this.createDateTime = createDateTime;
        this.user = user;
        this.issue = issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createDateTime=" + createDateTime +
                ", user=" + user +
                ", issue=" + issue +
                '}';
    }
}
