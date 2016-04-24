package com.company;

import com.company.model.User;
import com.company.service.UserService;

/**
 * Created by art on 23.04.2016.
 */
public class MainStart {

    public static void main(String[] args) {

        User user = new User("admin2","admin2","admin2");
        UserService userService = new UserService();
        System.out.println("id new user: " + new Long(userService.save(user)));
    }
}
