package com.user.repository;

import com.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findUserByIsAvailableTrue(Pageable pageable);

    User queryUserById(Long id);

    User queryUserByEmail(String email);
}
