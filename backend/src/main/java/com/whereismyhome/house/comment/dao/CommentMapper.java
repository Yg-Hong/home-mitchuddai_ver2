package com.whereismyhome.house.comment.dao;

import com.whereismyhome.house.comment.VO.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommentMapper {

    public void insertComment(Map<String, Object> params);

    public List<CommentVO> selectCommentsByAptCode(Long aptCode);
}
