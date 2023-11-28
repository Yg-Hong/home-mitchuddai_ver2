package com.whereismyhome.house.comment.service;

import com.whereismyhome.house.comment.VO.CommentVO;
import com.whereismyhome.house.comment.dao.CommentMapper;
import com.whereismyhome.house.comment.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public void addComment(Long aptCode, CommentDto commentDto) {
        Map<String, Object> params = new HashMap<>();

        params.put("text", commentDto.getText());
        params.put("user_id", commentDto.getAuthorId());
        params.put("aptCode", aptCode);
        params.put("created", new Date());

        commentMapper.insertComment(params);
    }

    public List<CommentVO> getCommentListByAptCode(Long aptCode) {
        return commentMapper.selectCommentsByAptCode(aptCode);
    }
}
