package com.company.model;

import java.sql.Timestamp;

public class Post {

    private Long id;
    private String topic;
    private String text;
    private Long userId;
    private Timestamp date;

    public Post(Long id, String topic, String text, Long userId, Timestamp date) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.userId = userId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
