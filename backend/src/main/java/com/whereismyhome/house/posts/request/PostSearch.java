package com.whereismyhome.house.posts.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
@Setter
public class PostSearch {

    private static final int MAX_SIZE = 2000;
    private Integer page;
    private Integer size;

    public PostSearch(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public long getOffset() {
        return (long) (max(1, page) - 1) * min(size, MAX_SIZE);
    }

}
