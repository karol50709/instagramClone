package com.kb.igClone.repository.mongo;

import com.kb.igClone.model.mongo.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
}
