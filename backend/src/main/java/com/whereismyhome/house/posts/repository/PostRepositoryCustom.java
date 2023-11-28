package com.whereismyhome.house.posts.repository;

import com.whereismyhome.house.posts.entity.Post;
import com.whereismyhome.house.posts.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);

}
