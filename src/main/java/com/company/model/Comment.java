package com.company.model;

import java.sql.Timestamp;

public class Comment {

    private Long id;
    private String text;
    private Timestamp date;
    private Long userId;
    private Long postId;

    public Comment(Long id, String text, Timestamp date, Long userId, Long postId) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.userId = userId;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
