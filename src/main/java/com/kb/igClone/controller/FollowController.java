package com.kb.igClone.controller;

import com.kb.igClone.bean.FollowBean;
import com.kb.igClone.service.FollowService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/newFollow")
    public Boolean follow(@RequestBody FollowBean followBean) {
        return followService.createFollow(followBean);
    }

    @GetMapping("/getFollowers/{login}")
    public List<String> getFollowers(@PathVariable String login) {
        return followService.getFollowers(login);
    }

    @DeleteMapping("/unfollow")
    public Boolean unfollow(@RequestBody FollowBean followBean) {
        return followService.unfollow(followBean);
    }
}
