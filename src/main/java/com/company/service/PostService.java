package com.company.service;

import com.company.dao.IPostDao;
import com.company.dao.PostDao;
import com.company.dto.PostDto;
import com.company.model.Post;
import com.company.model.User;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private static PostService instance = new PostService();

    public static PostService getInstance() {
        return instance;
    }

    public Post get(Long id) {
        return null;
    }

    public Post save(Post post) { return null; }

    public List<PostDto> list() { return null; }
}
