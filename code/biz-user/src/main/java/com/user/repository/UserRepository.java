package com.user.repository;

import com.user.domain.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User queryUserById(Long id);

    User queryUserByEmail(String email);
}
