package com.user.service;

import com.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserInfoService {

    Long createUser(User user);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    void updateUser(Long userId, User updatedUserInfo);

    void changeIsActiveStatus(Long userId, Boolean isActive);

    void touchUserAccount(Long userId);

    Page<User> findAllUsersByPage(Pageable pageable);
}
