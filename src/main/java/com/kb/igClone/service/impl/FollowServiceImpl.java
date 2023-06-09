package com.kb.igClone.service.impl;

import com.kb.igClone.bean.FollowBean;
import com.kb.igClone.exception.UserNotFoundException;
import com.kb.igClone.model.sql.Follow;
import com.kb.igClone.model.sql.User;
import com.kb.igClone.repository.sql.FollowRepository;
import com.kb.igClone.service.FollowService;
import com.kb.igClone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

    public FollowServiceImpl(FollowRepository followRepository,
                             UserService userService) {
        this.followRepository = followRepository;
        this.userService = userService;
    }


    @Override
    public Boolean createFollow(FollowBean followBean) {
        User watcher = userService.findUser(followBean.getWatcher());
        if(watcher == null){
            log.error("Watcher: {} not found", followBean.getWatcher());
            throw new UserNotFoundException(String.format("Watcher: %s not found", followBean.getWatcher()));
        }
        User followedUser = userService.findUser(followBean.getFollowed());
        if(followedUser == null) {
            log.error("Followed account: {} not found", followBean.getFollowed());
            throw new UserNotFoundException(String.format("Followed account: %s not found", followBean.getWatcher()));
        }
        Follow follow = new Follow();
        follow.setUser(watcher);
        follow.setFollowedUserId(followedUser.getId());
        follow.setCreationTime(LocalDateTime.now());
        followRepository.save(follow);
        return Boolean.TRUE;
    }

    @Override
    public List<String> getFollowers(String login) {
        User user = userService.findUser(login);
        if (user == null) {
            log.error("Followed account: {} not found", login);
            throw new UserNotFoundException(String.format("Followed account: %s not found", login));
        }
        List<String> followersLogins = user.getFollows()
                .stream()
                .map(Follow::getFollowedUserId)
                .map(userService::findUser)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(User::getLogin)
                .collect(Collectors.toList());
        return followersLogins;
    }

    @Override
    public Boolean unfollow(FollowBean followBean) {
        User watcher = userService.findUser(followBean.getWatcher());
        if(watcher == null){
            log.error("Watcher: {} not found", followBean.getWatcher());
            throw new UserNotFoundException(String.format("Watcher: %s not found", followBean.getWatcher()));
        }
        User followedUser = userService.findUser(followBean.getFollowed());
        if(followedUser == null) {
            log.error("Followed account: {} not found", followBean.getFollowed());
            throw new UserNotFoundException(String.format("Followed account: %s not found", followBean.getWatcher()));
        }
        Follow follow = followRepository.findByFollowedUserIdAndAndUser(followedUser.getId(), watcher);
        followRepository.delete(follow);
        return Boolean.TRUE;
    }
}
