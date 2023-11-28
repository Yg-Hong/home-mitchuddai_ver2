package com.whereismyhome.house.posts.controller;

import com.whereismyhome.house.posts.request.PostCreate;
import com.whereismyhome.house.posts.request.PostEdit;
import com.whereismyhome.house.posts.request.PostSearch;
import com.whereismyhome.house.posts.response.PostResponse;
import com.whereismyhome.house.posts.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

@Tag(name="Post API", description = "QnA 게시판 게시글의 CRUD를 위한 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @Operation(summary = "게시글 생성", description = "데이터를 입력받고 게시글로 저장합니다.")
    @Parameter(name = "request", description = "게시글 정보(title + content + authorId)")
    @PostMapping("")
    public Long post(@RequestBody @Valid PostCreate request) {
        return postService.write(request);
    }

    // 게시글 단건 조회
    @Operation(summary = "게시글 단건 조회", description = "등록되어 있는 게시글을 단건 조회합니다.")
    @Parameter(name = "postId", description = "게시글의 Key 값")
    @GetMapping("/{postId}")
    public PostResponse getSinglePost(@PathVariable Long postId) {
        return postService.getSinglePost(postId);
    }

    // 게시글 여러개 조회 - querydsl
    @Operation(summary = "게시글 복수건 조회", description = "등록되어 있는 게시글을 복수건 조회합니다.")
    @Parameter(name = "page", description = "사용자가 선택한 페이징 정보")
    @Parameter(name = "size", description = "사용자가 선택한 한 페이지에 보이는 게시글의 갯수 정보")
    @GetMapping("")
    public Map<String, Object> getList(@RequestParam(name="page") int page, @RequestParam(name="size") int size) {
        PostSearch postSearch = new PostSearch(page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("total", (int) Math.ceil(postService.getTotalSize() / (size+1))+1);
        map.put("Result", postService.getList(postSearch));
        return map;
    }

    // 게시글 수정
    @Operation(summary = "게시글 수정", description = "등록되어 있는 게시글을 수정합니다.")
    @Parameter(name = "postId", description = "게시글의 Key 값")
    @Parameter(name = "request", description = "수정하고자 하는 게시글 내용")
    @PatchMapping("/{postId}")
    public PostResponse edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        return postService.edit(postId, request);
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제", description = "등록되어 있는 게시글을 삭제합니다.")
    @Parameter(name = "postId", description = "게시글의 Key 값")
    @DeleteMapping("/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}