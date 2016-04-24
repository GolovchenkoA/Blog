package com.company.dto;

import com.company.model.Post;
import com.company.model.User;

import java.sql.Timestamp;

public class PostDto {
    private Post post;
    private User user;

    public PostDto(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
