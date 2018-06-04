package com.user.service;

import com.user.domain.entity.User;


public interface UserInfoService {

    Long createUser(User user);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    void updateUser(Long userId, User updatedUserInfo);

    void deleteUser(Long userId);

    boolean credentialCheck(User userInfo);
}
