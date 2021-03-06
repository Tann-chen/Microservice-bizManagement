package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;
import com.user.domain.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long createUser(User user) throws IllegalArgumentException {
        Assert.hasLength(user.getEmail(), "email not empty");
        Assert.hasLength(user.getName(), "name not empty");
        Assert.hasLength(user.getPassword(), "password not empty");

        Optional<User> existing = userRepository.queryUserByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("email has already used");
        }
        User created = userRepository.save(user);

        return created.getId();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.queryUserByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.queryUserById(userId);
    }

    @Override
    public User updateUser(Long userId, User updatedUserInfo) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepository.queryUserById(userId);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("user not existed"));
        if (!StringUtils.isEmpty(updatedUserInfo.getName())) {
            user.setName(updatedUserInfo.getName());
        }
        if (!StringUtils.isEmpty(updatedUserInfo.getEmail())) {
            user.setEmail(updatedUserInfo.getEmail());
        }
        if (!StringUtils.isEmpty(updatedUserInfo.getPassword())) {
            user.setPassword(updatedUserInfo.getPassword());
        }
        if (!StringUtils.isEmpty(updatedUserInfo.getPhone())) {
            user.setPhone(updatedUserInfo.getPhone());
        }
        if (updatedUserInfo.getJobStatus() != null) {
            user.setJobStatus(updatedUserInfo.getJobStatus());
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findUserByIsAvailableTrue();
    }


    @Override
    public void changeIsActiveStatus(Long userId, Boolean isActive) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepository.queryUserById(userId);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("user not existed"));
        user.setIsAvailable(isActive);
        userRepository.save(user);
    }


    @Override
    public Set<Permission> findPermissionsByUser(User user) {
        Set<Permission> permissionSet = new HashSet<>();

        //direct permissions
        List<Permission> directPermissions = user.getPermissionList();
        for (Permission p : directPermissions) {
            if (p.getIsAvailable()) {
                permissionSet.add(p);
            }
        }

        //role permissions
        List<Role> roles = user.getRoleList();
        for (Role r : roles) {
            if (r.getIsAvailable()) {
                List<Permission> permissions = r.getPermissionList();
                for (Permission p : permissions) {
                    if (p.getIsAvailable()) {
                        permissionSet.add(p);
                    }
                }
            }
        }

        return permissionSet;
    }


    @Override
    public ModulePermissions findModulePermissionsByUser(User user, Module module) {
        Set<Permission> permissionSet = findPermissionsByUser(user);
        ModulePermissions mp = new ModulePermissions();
        for (Permission p : permissionSet) {
            if (p.getModule().equals(module)) {
                mp.setFieldValue(p.getPermission().getType(), true);
            }
        }

        return mp;
    }
}
