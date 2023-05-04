package com.kb.igClone.mapper;

import com.kb.igClone.bean.NewUser;
import com.kb.igClone.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface NewUserToUserMapper {

    NewUser sourceToDestination(User source);

    User destinationToSource(NewUser destination);
}
