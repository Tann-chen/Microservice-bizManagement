package com.user.repository;

import com.user.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findUserByIsAvailableTrue();

    User queryUserById(Long id);

    User queryUserByEmail(String email);
}
