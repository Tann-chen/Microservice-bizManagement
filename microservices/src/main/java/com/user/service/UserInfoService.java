package com.user.service;

import com.user.comm.exception.BizException;
import com.user.domain.entity.User;

import java.util.List;


public interface UserInfoService {

    Long createUser(User user);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    void updateUser(Long userId, User updatedUserInfo);

    void deleteUser(Long userId);

    boolean credentialCheck(User userInfo);

    List<User> findAllUsers();
}
