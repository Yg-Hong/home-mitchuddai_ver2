package com.whereismyhome.house.posts.request;

import jakarta.persistence.Lob;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostCreate {

    private String title;

    @Lob
    private String content;

    private String authorId;

    @Builder
    public PostCreate(String title, String content, String authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

}
