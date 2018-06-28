package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Set;

public interface UserInfoService {

    Long createUser(User user);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    User updateUser(Long userId, User updatedUserInfo);

    void changeIsActiveStatus(Long userId, Boolean isActive);

    Page<User> findAllUsersByPage(Pageable pageable);

    Set<Permission> findPermissionsByUser(User user);

    ModulePermissions findModulePermissionsByUser(User user, Module module);
}
