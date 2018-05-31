package com.user.service;

import com.user.domain.entity.Permission;
import com.user.domain.entity.User;

import java.util.List;

public interface UserInfoService {

    User findUserByEmail(String email);

    boolean checkCredential(User userInfo);

    List<Permission> getPermissionsByUser(User user);
}
