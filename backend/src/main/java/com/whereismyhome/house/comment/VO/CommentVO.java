package com.whereismyhome.house.comment.VO;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private Long id;
    private String text;
    private String user_id;
    private String aptCode;
    private Date created;
}
