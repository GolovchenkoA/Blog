package com.company.service;

import com.company.dao.PostDao;
import com.company.dao.UserDao;
import com.company.dto.PostDto;
import com.company.model.Post;
import com.company.model.User;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();
    public Post get(Long id) {
        return postDao.get(id);
    }
    public Long save(Post post) { return postDao.save(post); }

    public List<PostDto> list() {
        UserDao userDao = new UserDao();
        List<PostDto> postDtoList = new ArrayList<>(); //список которы будем возвращать
        List<Post> postList = new PostDao().list(); // Объект из которого будем получать список постов

        // Если получили не пустой список постов
        if(postList!=null && !postList.isEmpty()){
            // дебаг
            try{
            System.out.println("Класс PostService. Метод List  создаем объект DTO: добавляем юзера: " + postList.toString());
            } catch(Exception e) { e.printStackTrace();
                System.out.println("ошибка в PostService при попытке postList.toString()"); }
            //

            //Итерация по списку постов и создание списка DTO
            for (Post post : postList){
                User user = userDao.get(post.getUserId());
                // При таком варианте была ошибка в Runtime java.lang.NoSuchMethodError:
                //User user = UserDao.getUser(post.getUserId()); //получаем пользователя, который создал пост

                // дебаг
                System.out.println("Класс PostService. Метод List  создаем объект DTO: добавляем пост и  юзера: " + user.toString());
                //
                PostDto postDto = new PostDto(post, user);
                postDtoList.add(postDto);
            }
        }
        System.out.println("Возвращаем объект DTO в List<PostDto> list()");
        return postDtoList;
    }
    //public List<Post> list() { return postDao.list(); }
}
