package com.whereismyhome.house.comment.controller;

import com.whereismyhome.house.comment.VO.CommentVO;
import com.whereismyhome.house.comment.dto.CommentDto;
import com.whereismyhome.house.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="댓글 API", description = "댓글 CRUD를 위한 API")
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "댓글 생성", description = "건물의 aptCode를 변수로 받아 해당 건물에 해당하는 사용자의 의견 댓글을 생성합니다.")
    @Parameter(name = "aptCode", description = "댓글을 등록할 건물의 code")
    @Parameter(name = "commentDto", description = "댓글 내용(text) + 댓글 작성자(authorId)")
    @PostMapping("/{aptCode}")
    public void addComment(@PathVariable Long aptCode, @RequestBody CommentDto commentDto) {
        commentService.addComment(aptCode, commentDto);
    }

    @Operation(summary = "댓글 조회", description = "건물의 aptCode를 변수로 받아 해당 건물에 해당하는 사용자의 의견 댓글을 조회합니다.")
    @Parameter(name = "aptCode", description = "댓글을 조회할 건물의 code")
    @GetMapping("/{aptCode}")
    public List<CommentVO> getCommentList(@PathVariable Long aptCode) {
        return commentService.getCommentListByAptCode(aptCode);
    }
}
