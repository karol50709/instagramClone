package com.kb.igClone.service;

import com.kb.igClone.bean.NewUser;
import com.kb.igClone.model.User;

public interface UserService {

    User createNewUser(NewUser user);
}
