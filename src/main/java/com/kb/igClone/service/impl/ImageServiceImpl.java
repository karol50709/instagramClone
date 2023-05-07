package com.kb.igClone.service.impl;

import com.kb.igClone.bean.ImagesSaveResultBean;
import com.kb.igClone.exception.ImageSaveException;
import com.kb.igClone.exception.UserNotFoundException;
import com.kb.igClone.model.mongo.Image;
import com.kb.igClone.model.mongo.Thumbnail;
import com.kb.igClone.model.sql.User;
import com.kb.igClone.repository.mongo.ImageRepository;
import com.kb.igClone.repository.mongo.ThumbnailRepository;
import com.kb.igClone.service.ImageService;
import com.kb.igClone.service.UserService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final ThumbnailRepository thumbnailRepository;

    private final UserService userService;


    public ImageServiceImpl(ImageRepository imageRepository,
                            ThumbnailRepository thumbnailRepository,
                            UserService userService) {
        this.imageRepository = imageRepository;
        this.thumbnailRepository = thumbnailRepository;
        this.userService = userService;
    }

    @Override
    public ImagesSaveResultBean save(byte[] bytes, User user) throws ImageSaveException {
        try {
            Image image = new Image();
            image.setUserId(user.getId());
            image.setLogin(user.getLogin());
            image.setData(new Binary(BsonBinarySubType.BINARY, bytes));
            Image savedImage = imageRepository.save(image);
            Thumbnail thumbnail = new Thumbnail();
            thumbnail.setLogin(user.getLogin());
            thumbnail.setUserId(user.getId());
            Thumbnail savedThumbnail = thumbnailRepository.save(thumbnail);
            return new ImagesSaveResultBean(savedImage.getId(), savedThumbnail.getId());
        } catch (Exception e) {
            throw new ImageSaveException(e);
        }
    }

    @Override
    public Optional<Image> getImage(String id, String login) {
        if(userService.userExist(login)){
            throw new UserNotFoundException(String.format("Account: %s not found", login));
        }
        return imageRepository.findById(id);
    }

    @Override
    public Optional<Thumbnail> getThumbnail(String id, String login) {
        if(userService.userExist(login)){
            throw new UserNotFoundException(String.format("Account: %s not found", login));
        }
        return thumbnailRepository.findById(id);
    }
}
