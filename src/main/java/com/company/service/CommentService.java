package com.company.service;

import com.company.dao.CommentDao;
import com.company.dao.ICommentDao;
import com.company.dto.CommentDto;
import com.company.model.Comment;
import com.company.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommentService {

    private static CommentService instance = new CommentService();

    public static CommentService getInstance() {
        return instance;
    }

    public Comment save(Comment comment) { return null; }

    public List<CommentDto> listByPost(Long postId) { return null; }
}
