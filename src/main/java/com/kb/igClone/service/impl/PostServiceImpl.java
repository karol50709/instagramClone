package com.kb.igClone.service.impl;

import com.kb.igClone.bean.ImagesSaveResultBean;
import com.kb.igClone.exception.ImageSaveException;
import com.kb.igClone.exception.ServerErrorException;
import com.kb.igClone.exception.UserNotFoundException;
import com.kb.igClone.mapper.PostBeanToPostMapper;
import com.kb.igClone.mapper.PostBeanToPostMapperImpl;
import com.kb.igClone.model.sql.Post;
import com.kb.igClone.model.sql.User;
import com.kb.igClone.repository.sql.PostRepository;
import com.kb.igClone.service.ImageService;
import com.kb.igClone.service.PostService;
import com.kb.igClone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    private final ImageService imageService;

    private final PostBeanToPostMapper mapper;

    public PostServiceImpl(PostRepository postRepository,
                           UserService userService,
                           ImageService imageService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.imageService = imageService;
        this.mapper = new PostBeanToPostMapperImpl();
    }

    @Override
    public void createPost(MultipartFile file, String login, String description) {
        User user = userService.findUser(login);
        if(user == null) {
            log.error("Followed account: {} not found", login);
            throw new UserNotFoundException(String.format("Followed account: %s not found", login));
        }
        Post entity = new Post();
        entity.setDescription(description);
        entity.setUser(user);
        try {
            ImagesSaveResultBean save = imageService.save(file.getBytes(), user);
            if(StringUtils.isEmpty(save.getThumbnailMongoId()) || StringUtils.isEmpty(save.getImageMongoId())){
                throw new ServerErrorException("Problem with save image");
            }
            entity.setImageMongoId(save.getImageMongoId());
            entity.setThumbnailMongoId(save.getThumbnailMongoId());
        } catch (ImageSaveException | IOException e) {
            throw new ServerErrorException(e);
        }
        entity.setCreationTime(LocalDateTime.now());
        postRepository.save(entity);
    }
}
