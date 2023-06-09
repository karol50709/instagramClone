package com.kb.igClone.repository.sql;

import com.kb.igClone.model.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByLogin(String login);

    User findByLogin(String login);


}
