package com.kb.igClone.service;

import com.kb.igClone.bean.PostBean;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    void createPost(MultipartFile file, String login, String description);
}
