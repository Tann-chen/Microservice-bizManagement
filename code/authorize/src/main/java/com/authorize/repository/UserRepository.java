package com.authorize.repository;

import com.authorize.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User queryByEmail(String email);
}
