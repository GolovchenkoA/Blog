package com.company.dao;

import com.company.model.Comment;

import java.util.List;

public interface ICommentDao {

    Long save(Comment coment);
    List<Comment> listByPost(Long postId);
}
