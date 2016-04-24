package com.company.dao;

import com.company.model.User;

import java.sql.SQLException;

public interface IUserDao {

    User get(Long id) throws SQLException;
    User findByLogin(String login) throws SQLException;
    Long save(User user) throws SQLException;
}
