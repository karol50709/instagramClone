package com.kb.igClone.controller;

import com.kb.igClone.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/new", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Boolean createPost(@RequestParam("login") String login,
                              @RequestParam("description") String description,
                              @RequestPart("file") MultipartFile file) {
        postService.createPost(file, login, description);
        return Boolean.TRUE;
    }

    @GetMapping("/getPosts")
    public Object getPostsFromUser(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam("login") String login
    ) {
        return new ArrayList<>();
    }

}
