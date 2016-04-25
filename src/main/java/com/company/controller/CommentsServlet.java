package com.company.controller;

import com.company.dao.CommentDao;
import com.company.dao.UserDao;
import com.company.model.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/new_comment"})
public class CommentsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url;
        // Если запрос на добавление нового комментария
        if(req.getRequestURI().equals("/new_comment")){

            Comment comment = new Comment();
            UserDao userDao = new UserDao();

            String comment_text = req.getParameter("comment_text");
/*            String user_login = (String) req.getSession().getAttribute("login");
            User user = userDao.findByLogin(user_login);*/

            Long post_id = (long) Integer.parseInt(req.getParameter("post_id"));
            Long user_id = (long) Integer.parseInt(req.getParameter("user_id"));

            // Инициализируем объект комментария
            comment.setText(comment_text);
            comment.setUserId(user_id);
            comment.setPostId(post_id);
/*            // Debug
            System.out.println("post_id CommentsServlet method doPost:" + post_id);
            System.out.println("user_id CommentsServlet method doPost:" + user_id);
            System.out.println("comment_text CommentsServlet method doPost:" + comment_text);*/
            System.out.println(comment);

            // Сохраняем комментарий в БД
            CommentDao commentDao = new CommentDao();
            System.out.println(comment.toString());
            Long commentID = commentDao.save(comment);

            // Если комментарий сохранили в БД перенаправляем на тот же пост
            if(!commentID.equals(0L)){
                url = "/post/" + post_id;
                resp.sendRedirect(url);
            }

        }

/*        // Если попали на страницу с определенного поста туда же и перенаправляем
        if(req.getParameter("post_id") != null){
            url = "/post/" + req.getParameter("post_id");
            resp.sendRedirect(url);
        }*/

        // По умолчанию всех на первый пост  (ЗАГЛУШКА)
        //resp.sendRedirect("/post/1");

    }
}
