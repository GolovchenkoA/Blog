package com.company.dao;

import com.company.model.Post;
import com.company.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements IPostDao {

    @Override
    public Post get(Long id) {

        Post post = new Post();

        try(Connection connection = ConnectionUtil.createConnection()){

            PreparedStatement statement = connection.prepareStatement("SELECT post_id,post_title,text,user_id,created FROM posts where post_id = ? ");
            statement.setLong(1,id);
            System.out.println("Find post : " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                post = extractPost(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public Long save(Post post) {

        Long postID = 0L;

        try(Connection connection = ConnectionUtil.createConnection()){
            String query = "INSERT INTO posts (post_title,text,user_id) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,post.getTopic());
            statement.setString(2,post.getText());
            statement.setLong(3,post.getUserId());

            // поставил что бы автоматически генерировалось на стороне БД
            //statement.setDate(4,(new Date(post.getDate().getTime())));

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                postID = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println("Произошла ошибка при попытке сохранить пост в БД");
            e.printStackTrace();
        }

        return postID;
    }

    @Override
    public List<Post> list() {

        List<Post> postList = new ArrayList<>();
        try(Connection connection = ConnectionUtil.createConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts LIMIT 10");
            ResultSet resultSet = statement.executeQuery();

            // Добавляем найденые посты в список
            while (resultSet.next()){
                postList.add(extractPost(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postList;
    }

    private Post extractPost(ResultSet resultSet) throws SQLException {

        Post post = new Post();
        post.setId(resultSet.getLong("post_id"));
        post.setTopic(resultSet.getString("post_title"));
        post.setText(resultSet.getString("text"));
        post.setUserId(resultSet.getLong("user_id"));
        post.setDate(resultSet.getTimestamp("created"));

                /*        return new Post(
                resultSet.getLong("id"),
                resultSet.getString("topic"),
                resultSet.getString("text"),
                resultSet.getLong("user_id"),
                resultSet.getTimestamp("date"));
    }*/
        return  post;
    }

}
