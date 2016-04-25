package com.company;

import com.company.model.Post;
import com.company.service.PostService;

/**
 * Created by art on 23.04.2016.
 */
public class MainStart {

    public static void main(String[] args) {




        /* ТЕСТЫ С СОЗДАНИЕМ\ ПОЛУЧЕНИЕМ ПОЛЬЗОВАТЕЛЯ
        UserService userService = new UserService();
        User user = new User();
        String login = "usr";

        user.setLogin(login);
        user.setPassword("pwd");
        user.setUsername("usrName");

       *//* System.out.println(userService.findByLogin("admin"));*//*
        User foundUser = userService.findByLogin(login);

        System.out.println(foundUser);

        if(foundUser.getId() != null){
            // Если пользователь существует
            System.out.println("User with login: [" + login + "] already exists");
            // forward request and response objects to specified URL
           // getServletContext().getRequestDispatcher("/signup").forward(req, resp);

        }else {

            Long userID = userService.save(user);
            //getServletContext().getRequestDispatcher("/login").forward(req, resp);
            //Если получили ID пользователя отправляем на станицу логина
            if (!userID.equals(0L)){
                System.out.println("Пользователь сохраннен в БД");
            }else {
                System.out.println("Пользователя не удалось сохранить в БД");
                // forward request and response objects to specified URL
                //getServletContext().getRequestDispatcher("/signup").forward(req, resp);
            }

        }
*/



/*        if(userService.findByLogin("admin").equals(new User())){
            System.out.println(userService.findByLogin("admin").equals(new User()));
        }else {
            System.out.println("if не отработало");
        }*/
/*        User user = new User("admin","admin","admin");
        UserService userService = new UserService();
        System.out.println("id new user: " + new Long(userService.save(user)));*/

/*
        *//*ТЕСТЫ С СОЗДАНИЕМ\ ПОЛУЧЕНИЕМ Поста*//*

        PostService postService = new PostService();
        List<PostDto> postList = postService.list();
        //System.out.println(postList);
        for(PostDto postDto:postList){
            System.out.println("Post: " + postDto.getPost().getTopic() + " User: " + postDto.getUser().toString() );
        }*/

/*
        /*//*ТЕСТЫ С regexp*//**//*

        String text ="/posts";
        String patternString = "^(/post)/(\\d+)"; // ".*http://.*";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);*/


        PostService postService = new PostService();
        Post post = postService.get(1L);
        post.toString();


    }
}
