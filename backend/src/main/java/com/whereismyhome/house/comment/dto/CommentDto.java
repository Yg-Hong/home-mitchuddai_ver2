package com.whereismyhome.house.comment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private String text;
    private String authorId;
}
