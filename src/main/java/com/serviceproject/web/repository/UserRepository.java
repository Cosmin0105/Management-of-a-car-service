package com.serviceproject.web.repository;

import com.serviceproject.web.models.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);

    UserEntity findFirstByUsername(String username);
}
