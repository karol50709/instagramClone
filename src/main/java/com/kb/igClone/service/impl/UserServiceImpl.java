package com.kb.igClone.service.impl;

import com.kb.igClone.bean.NewUser;
import com.kb.igClone.exception.UserExistException;
import com.kb.igClone.mapper.NewUserToUserMapper;
import com.kb.igClone.mapper.NewUserToUserMapperImpl;
import com.kb.igClone.model.sql.User;
import com.kb.igClone.repository.sql.UserRepository;
import com.kb.igClone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final NewUserToUserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new NewUserToUserMapperImpl();
    }

    @Override
    public User createNewUser(NewUser user) {
        User newUser = userMapper.destinationToSource(user);
        if(userRepository.existsByLogin(newUser.getLogin())){
            log.error("User exists with login: {}", newUser.getLogin());
            throw new UserExistException();
        }
        newUser.setCreationTime(LocalDateTime.now());
        User save = userRepository.save(newUser);
        log.info("Created user with login: {}", newUser.getLogin());
        return save;
    }

    @Override
    public User findUser(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findUser(Long id){
        return userRepository.findById(id);
    }

    @Override
    public Boolean userExist(String login) {
        return userRepository.existsByLogin(login);
    }
}
