package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.User;
import java.util.List;
import java.util.Set;


public interface UserInfoService {

    Long createUser(User user);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    User updateUser(Long userId, User updatedUserInfo);

    void changeIsActiveStatus(Long userId, Boolean isActive);

    List<User> findAllUsersByPage();

    Set<Permission> findPermissionsByUser(User user);

    ModulePermissions findModulePermissionsByUser(User user, Module module);
}
