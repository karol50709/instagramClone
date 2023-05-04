package com.kb.igClone.service;

import com.kb.igClone.bean.FollowBean;

import java.util.List;

public interface FollowService {

    Boolean createFollow(FollowBean followBean);

    List<String> getFollowers(String login);

    Boolean unfollow(FollowBean followBean);
}
