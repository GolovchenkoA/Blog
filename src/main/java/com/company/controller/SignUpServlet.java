package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url;
        String action = req.getRequestURI();
        System.out.println(action);

        if (action.equals("/signup")){

            //Long user_id = (long) Integer.parseInt(req.getParameter("user_id"));
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String username = req.getParameter("username");

            User user = new User(login,password,username);


            // Пробуем создать нового пользователя
            UserService userService = new UserService();
            User foundUser = userService.findByLogin(login);

                // Если пользователя с таким логином в базе нет
                if(foundUser.getId() != null){
                    // Если пользователь существует
                    System.out.println("User with login: [" + login + "] already exists");
                    req.setAttribute("message", "User with login: [" + login + "] already exists");

                    // forward request and response objects to specified URL
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(req, resp);
                    //url = "/WEB-INF/jsp/signUp.jsp";

                }else {

                        Long userID = userService.save(user);
                        //getServletContext().getRequestDispatcher("/login").forward(req, resp);
                    //Если получили ID пользователя отправляем на станицу логина
                    if (!userID.equals(0L)){
                        resp.sendRedirect("/login");
                    }else {
                        System.out.println("Пользователя не удалось сохранить в БД");
                        req.setAttribute("message", "Пользователя не удалось сохранить в БД");
                        // forward request and response objects to specified URL
                        getServletContext().getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(req, resp);
                        //url = "/WEB-INF/jsp/signUp.jsp";
                    }

                }


        }
    }
}
