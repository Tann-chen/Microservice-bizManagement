package com.user.repository;

import com.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User queryUserById(Integer id);

    User queryUserByEmail(String email);
}
