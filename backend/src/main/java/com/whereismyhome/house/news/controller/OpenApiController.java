package com.whereismyhome.house.news.controller;

import com.whereismyhome.house.news.api.NaverNewsApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@Slf4j
public class OpenApiController {

    @Autowired
    NaverNewsApi naver = new NaverNewsApi();

    @GetMapping("/news")
    public ResponseEntity<JSONObject> getNews() throws Exception {

        JSONParser parser = new JSONParser();
        Object keyword = parser.parse(naver.search("부동산"));
        JSONObject obj = (JSONObject) keyword;

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }


}