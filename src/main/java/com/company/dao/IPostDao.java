package com.company.dao;

import com.company.model.Post;

import java.util.List;

public interface IPostDao {

    Post get(Long id);
    Long save(Post post);
    List<Post> list();
}
