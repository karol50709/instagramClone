package com.kb.igClone.service;

import com.kb.igClone.bean.NewUser;
import com.kb.igClone.model.sql.User;

import java.util.Optional;

public interface UserService {

    User createNewUser(NewUser user);

    User findUser(String login);

    Optional<User> findUser(Long id);

    Boolean userExist(String login);
}
