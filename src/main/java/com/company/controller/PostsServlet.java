package com.company.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("PostsServlet. doGet. User " +req.getParameter("login") +" forward to post.jsp");
        System.out.println("Пользователь " + req.getParameter("login") + " попал в метод doPost PostsServlet");
        req.getRequestDispatcher("/WEB-INF/jsp/post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getRequestURI();

        if(action.equals("/new_post")){
            String post_title = req.getParameter("post_title");
            String post_text = req.getParameter("post_text");
            //String file = req.getParameter("file");  // Прикрутить позже
            Date post_time = new Date();
        }

    }
}
