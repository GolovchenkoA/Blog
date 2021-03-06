package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/logout")) {
            System.out.println("User " + req.getSession().getAttribute("login") + " logout");
            req.getSession().removeAttribute("login");
            req.getSession().removeAttribute("username");
            resp.sendRedirect("/login");
            return;

        }

        // Если пользователь уже залогинился и снова переходит на страницу логина - перенаправляем на посты
        if (req.getSession().getAttribute("login") != null) {
            resp.sendRedirect("/posts/1"); // На первый пост
            return;
        }
        // если еще не залогинился - отправляем на страницу ауткнтификации
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getRequestURI().equals("/login")){

            UserService userService = new UserService();
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            User user = userService.findByLogin(login);

            // Если пользователь найден и Пароль совпадает
            if(user.getLogin() !=null && user.getLogin().equals(login) && user.getPassword().equals(password)){

                HttpSession session = req.getSession(true);
                session.setMaxInactiveInterval(30 * 60);
                session.setAttribute("userid", user.getId());
                session.setAttribute("login", user.getLogin());
                System.out.println("Выполняем resp.sendRedirect(\"/posts\");");
                resp.sendRedirect("/posts");
            }else { // Если пользователь не найден или пароль пользователя не совпадет
                req.setAttribute("message","Логин или пароль не совпадает");
                //req.getRequestDispatcher("/login").forward(req,resp); //отправляем обратно в doPost если пользователя нет в базе (бесконечный цикл)
                resp.sendRedirect("/login");  // Если логина нет - отправляет в метод doGet
            }
        }

    }

}
