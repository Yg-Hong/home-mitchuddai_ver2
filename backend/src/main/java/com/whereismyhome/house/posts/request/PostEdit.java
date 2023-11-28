package com.whereismyhome.house.posts.request;

import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostEdit {

    private String title;

    @Lob
    private String content;

    @Builder
    public PostEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
