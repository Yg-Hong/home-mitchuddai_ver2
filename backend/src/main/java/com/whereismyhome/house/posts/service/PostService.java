package com.whereismyhome.house.posts.service;

import com.whereismyhome.house.exception.PostNotFound;
import com.whereismyhome.house.posts.entity.Post;
import com.whereismyhome.house.posts.repository.PostRepository;
//import com.whereismyhome.house.posts.repository.PostRepositoryImpl;
import com.whereismyhome.house.posts.request.PostCreate;
import com.whereismyhome.house.posts.request.PostEdit;
import com.whereismyhome.house.posts.request.PostSearch;
import com.whereismyhome.house.posts.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    // 게시글 작성
    @Transactional
    public Long write(PostCreate postCreate) {
        // postCreateDto -> Entity 로 type cast 과정 필요
        Post post = new Post(postCreate.getTitle(), postCreate.getContent(), postCreate.getAuthorId());
        Post saved = postRepository.save(post);
        return saved.getId();
    }

    // 게시글 단건 조회
    public PostResponse getSinglePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorId(post.getAuthorId())
                .build();
    }

    // 게시글 여러개 조회 - querydsl
    public List<PostResponse> getList(PostSearch postSearch) {
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    // 전체 글 개수 반환
    public Integer getTotalSize() {
        return postRepository.findAll().size();
    }

    // 게시글 수정
    @Transactional
    public PostResponse edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        post.edit(postEdit);
        return new PostResponse(post);
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long id) {
        Post postToDelete = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        postRepository.delete(postToDelete);
    }
}
