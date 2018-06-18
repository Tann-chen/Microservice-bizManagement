package com.user.service;

import com.user.domain.entity.Permission;
import com.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserInfoService {

    Long createUser(User user);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    void updateUser(Long userId, User updatedUserInfo);

    void changeIsActiveStatus(Long userId, Boolean isActive);

    Page<User> findAllUsersByPage(Pageable pageable);

    Set<Permission> findPermissionsByUser(User user);
}
