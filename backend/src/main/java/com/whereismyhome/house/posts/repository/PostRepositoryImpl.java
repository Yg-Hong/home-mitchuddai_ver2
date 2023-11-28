package com.whereismyhome.house.posts.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.whereismyhome.house.posts.entity.Post;
import com.whereismyhome.house.posts.request.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.whereismyhome.house.posts.entity.QPost.post;

/**
 * QueryDsl 이용한 게시글 여러개 조회
 */

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }
}
