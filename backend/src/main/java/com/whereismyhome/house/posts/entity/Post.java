package com.whereismyhome.house.posts.entity;

import com.whereismyhome.house.house.HouseType;
import com.whereismyhome.house.posts.request.PostEdit;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private HouseType bldg_type;

    private String title;

    @Lob // 자바에서는 String, DB 에서는 Long text 로 넘어가도록
    private String content;

    private String authorId;

    private String createdAt;
    private String updatedAt;

    @Builder
    public Post(String title, String content, String authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        this.createdAt = formattedDate;
        this.updatedAt = formattedDate;
    }

    // 글 수정 로직
    public void edit(PostEdit postEdit) {
        title =  postEdit.getTitle() != null ? postEdit.getTitle() : title;
        content = postEdit.getContent() != null ? postEdit.getContent() : content;
    }

}
