package com.company.dao;

import com.company.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostDao implements IPostDao {

    @Override
    public Post get(Long id) {
        return null;
    }

    @Override
    public Long save(Post post) {
        return null;
    }

    @Override
    public List<Post> list() {
        return null;
    }

    private Post extractPost(ResultSet resultSet) throws SQLException {
        return new Post(
                resultSet.getLong("id"),
                resultSet.getString("topic"),
                resultSet.getString("text"),
                resultSet.getLong("user_id"),
                resultSet.getTimestamp("date"));
    }
}
