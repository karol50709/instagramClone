package com.kb.igClone.repository.mongo;

import com.kb.igClone.model.mongo.Thumbnail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbnailRepository extends MongoRepository<Thumbnail, String> {
}

