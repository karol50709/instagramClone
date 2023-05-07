package com.kb.igClone.mapper;

import com.kb.igClone.bean.PostBean;
import com.kb.igClone.model.sql.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostBeanToPostMapper {

    PostBean sourceToDestination(Post source);

    Post destinationToSource(PostBean destination);
}
