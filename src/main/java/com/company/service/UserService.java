package com.company.service;

import com.company.dao.IUserDao;
import com.company.dao.UserDao;
import com.company.model.User;

import java.sql.SQLException;

public class UserService implements IUserDao {

    private static UserDao instance = new UserDao();

/*
    public static UserService getInstance() {
        return instance;
    }*/

    public User get(Long id){
        return instance.get(id);
    }

    public User findByLogin(String login){
        return instance.findByLogin(login);
    }
    public Long save(User user){
        return instance.save(user);
    }
}
