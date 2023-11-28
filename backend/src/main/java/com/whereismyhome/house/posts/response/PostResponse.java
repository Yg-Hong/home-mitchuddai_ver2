package com.whereismyhome.house.posts.response;


import com.whereismyhome.house.posts.entity.Post;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    @Lob
    private final String content;
    private final String authorId;
    private final String createdAt;

    // 생성자 오버로딩
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.authorId = post.getAuthorId();
        this.createdAt = post.getCreatedAt();
    }

    @Builder
    public PostResponse(Long id, String title, String content, String authorId, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }
}
