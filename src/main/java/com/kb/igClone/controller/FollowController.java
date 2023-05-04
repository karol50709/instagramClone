package com.kb.igClone.controller;

import com.kb.igClone.bean.FollowBean;
import com.kb.igClone.service.FollowService;
import org.springframework.web.bind.annotation.*;

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
    public List<String> getFollowers(@PathVariable String login){
        return followService.getFollowers(login);
    }

    @DeleteMapping("/unfollow")
    public Boolean unfollow(@RequestBody FollowBean followBean) {
        return followService.unfollow(followBean);
    }
}
