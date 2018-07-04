package com.user.repository;

import com.user.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findUserByIsAvailableTrue();

    Optional<User> queryUserById(Long id);

    Optional<User> queryUserByEmail(String email);
}
