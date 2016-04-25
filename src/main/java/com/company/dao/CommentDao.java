package com.company.dao;

import com.company.model.Comment;
import com.company.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements ICommentDao {

    @Override
    public Long save(Comment comment) {

        Long commentID = 0L; // Значение если не получиться сохранить

        try(Connection connection = ConnectionUtil.createConnection()){
            String query = "INSERT INTO comments (user_id, post_id, text) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query ,Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1,comment.getUserId());
            statement.setLong(2,comment.getPostId());
            statement.setString(3,comment.getText());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                commentID = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentID;
    }

    @Override
    public List<Comment> listByPost(Long postId) {

        List<Comment> commentList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.createConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE post_id = ?");
            statement.setLong(1, postId);
            ResultSet resultSet = statement.executeQuery();

            // Добавляем найденые комментарии в список
            while (resultSet.next()) {
                commentList.add(extractComment(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }

    private Comment extractComment(ResultSet resultSet) throws SQLException {

        Comment comment = new Comment();
        comment.setId(resultSet.getLong("comment_id"));
        comment.setUserId(resultSet.getLong("user_id"));
        comment.setPostId(resultSet.getLong("post_id"));
        comment.setDate(resultSet.getTimestamp("created"));
        comment.setText(resultSet.getString("text"));

        return comment;

        /*        return new Comment(
                resultSet.getLong("id"),
                resultSet.getString("text"),
                resultSet.getTimestamp("date"),
                resultSet.getLong("user_id"),
                resultSet.getLong("post_id"));
    }*/
    }
}
