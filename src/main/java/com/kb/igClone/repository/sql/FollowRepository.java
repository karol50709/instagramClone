package com.kb.igClone.repository.sql;

import com.kb.igClone.model.sql.Follow;
import com.kb.igClone.model.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findByFollowedUserIdAndAndUser(Long followedUserId, User user);
}
