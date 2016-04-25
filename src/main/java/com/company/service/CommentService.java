package com.company.service;

import com.company.dao.CommentDao;
import com.company.model.Comment;

import java.util.List;

public class CommentService {

    private CommentDao commentDao = new CommentDao();

    public Long save(Comment comment) { return commentDao.save(comment); }

    //public List<CommentDto> listByPost(Long postId) { return null; }
    public List<Comment> listByPost(Long postId) { return commentDao.listByPost(postId); }
}
