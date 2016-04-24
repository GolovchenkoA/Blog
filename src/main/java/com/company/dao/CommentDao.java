package com.company.dao;

import com.company.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentDao implements ICommentDao {

    @Override
    public Long save(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> listByPost(Long postId) {
        return null;
    }

    private Comment extractComment(ResultSet resultSet) throws SQLException {
        return new Comment(
                resultSet.getLong("id"),
                resultSet.getString("text"),
                resultSet.getTimestamp("date"),
                resultSet.getLong("user_id"),
                resultSet.getLong("post_id"));
    }
}
