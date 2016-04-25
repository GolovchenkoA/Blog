package com.company.controller;

import com.company.dao.UserDao;
import com.company.dto.PostDto;
import com.company.model.Comment;
import com.company.model.Post;
import com.company.model.User;
import com.company.service.CommentService;
import com.company.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns ={"/posts","/new_post","/post/*"}, name="postsServlet")
public class PostsServlet extends HttpServlet {

    // Список постов, которые будут отображаться под формой создание нового поста
    PostService postService = new PostService();
    List<PostDto> postDtoList = postService.list();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("PostsServlet. doGet. User " +req.getParameter("login") +" forward to post.jsp");
        //System.out.println("Пользователь "  + req.getRemoteUser() + " попал в метод doGet PostsServlet. ПРОВЕРКА НЕ ОТРАБАТЫВАЕТ");
        System.out.println("Пользователь "  + req.getSession().getAttribute("login") + " попал в метод doGet PostsServlet.");
/*
        // Для дебага
        System.out.println(postDtoList.toString());
        for(PostDto postDto:postDtoList){
            System.out.println("From PostsServlet. Post: " + postDto.getPost().getTopic() + " User: " + postDto.getUser().toString() );
        }
        // Конец дебага

*/
        // парсим строку перенаправления на пост

        //String text ="/post/1";
        String patternString = "^(/post)/(\\d+)"; // ".*http://.*";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(req.getRequestURI());
        boolean matches = matcher.matches();
        //System.out.println("matches = " + matches);

        //Если паттерн совпадет
        if(matches){
            // Получаем объект поста
            PostService postService = new PostService();
            String postIDstr = req.getRequestURI().replace("/post/","");
            Long postID = (long) (new Integer(postIDstr));
            Post post = postService.get(postID);
            User user = new UserDao().get(post.getUserId());

            //Post DTO Object
            PostDto postDto = new PostDto(post,user);
            System.out.println("Debug: PostsServlet postDTO object: " + postDto);
            req.setAttribute("postDTO", postDto);

            // Получаем список комментариев к теме
            CommentService commentService = new CommentService();
            List<Comment> commentList = commentService.listByPost(postID);
            req.setAttribute("commentList",commentList);

            req.getRequestDispatcher("/WEB-INF/jsp/post.jsp").forward(req, resp);
        }else { // Для всех остальных запросов
            req.setAttribute("postDTOList", postDtoList);
            req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
        }


        req.setAttribute("postDTOList", postDtoList);
        req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Пользователь " + req.getRemoteUser() + " попал в метод doPost PostsServlet. ПРОВЕРКА НЕ ОТРАБАТЫВАЕТ");
        String action = req.getRequestURI();

        if(action.equals("/new_post")){
            UserDao userDao = new UserDao();
            User user = userDao.findByLogin(req.getSession().getAttribute("login").toString());
            String post_title = req.getParameter("post_title");
            String post_text = req.getParameter("post_text");
            //String file = req.getParameter("file");  // Прикрутить позже

            java.util.Date date= new java.util.Date();
            java.sql.Timestamp post_time = new Timestamp(date.getTime());
            Post post = new Post(post_title,post_text, user.getId(),post_time);

            Long postID = postService.save(post);

            //Если пост удалось сохранить и метод вернул ИД поста
            if(!postID.equals(0L)){
                // Добавляем в список новый пост
                PostDto postDto = new PostDto(post,user);
                postDtoList.add(postDto);
                System.out.println("Пользователь сохранен в БД");
                // Перенаправляем снова на страницу постов
                req.setAttribute("postDTOList", postDtoList);
                //req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
            } else { // если пост не удалось сохранить в БД
                System.out.println("Пользователя не удалось сохранить в БД");
                req.setAttribute("message", "Пользователя не удалось сохранить в БД");
                //req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
            }

        }
        req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
    }
}
