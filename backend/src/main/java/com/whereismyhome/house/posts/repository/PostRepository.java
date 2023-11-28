package com.whereismyhome.house.posts.repository;

import com.whereismyhome.house.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
