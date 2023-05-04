package com.kb.igClone.repository;

import com.kb.igClone.model.Follow;
import com.kb.igClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findByFollowedUserIdAndAndUser(Long followedUserId, User user);
}
