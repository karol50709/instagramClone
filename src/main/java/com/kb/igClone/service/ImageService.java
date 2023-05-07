package com.kb.igClone.service;

import com.kb.igClone.bean.ImagesSaveResultBean;
import com.kb.igClone.exception.ImageSaveException;
import com.kb.igClone.model.mongo.Image;
import com.kb.igClone.model.mongo.Thumbnail;
import com.kb.igClone.model.sql.User;

import java.util.Optional;

public interface ImageService {

    ImagesSaveResultBean save(byte[] bytes, User user) throws ImageSaveException;

    Optional<Image> getImage(String id, String login);

    Optional<Thumbnail> getThumbnail(String id, String login);
}
