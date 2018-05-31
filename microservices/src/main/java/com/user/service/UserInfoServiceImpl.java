package com.user.service;

import com.user.domain.entity.Permission;
import com.user.domain.entity.User;

import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean checkCredential(User userInfo) {
        return false;
    }

    @Override
    public List<Permission> getPermissionsByUser(User user) {
        return null;
    }
}
