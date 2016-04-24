package com.company.dto;

import com.company.model.Comment;
import com.company.model.User;

public class CommentDto {

    private Comment comment;
    private User user;

    public CommentDto(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
