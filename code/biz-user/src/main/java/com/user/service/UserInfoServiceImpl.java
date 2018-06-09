package com.user.service;

import com.user.domain.entity.User;
import com.user.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Timestamp;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long createUser(User user) throws IllegalArgumentException {
        Assert.hasLength(user.getEmail(), "email not empty");
        User existing = userRepository.queryUserByEmail(user.getEmail());
        Assert.isNull(existing, "email already used:" + user.getEmail());
        Assert.hasLength(user.getName(), "name not empty");
        Assert.hasLength(user.getPassword(), "password not empty");
        User created = userRepository.save(user);

        return created.getId();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.queryUserByEmail(email);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.queryUserById(userId);
    }

    @Override
    public void updateUser(Long userId, User updatedUserInfo) throws IllegalArgumentException {
        User user = userRepository.queryUserById(userId);
        Assert.notNull(user, "user not existed");
        if(StringUtils.isNotEmpty(updatedUserInfo.getName())){
            user.setName(updatedUserInfo.getName());
        }
        if(StringUtils.isNotEmpty(updatedUserInfo.getEmail())){
            user.setEmail(updatedUserInfo.getEmail());
        }
        if(StringUtils.isNotEmpty(updatedUserInfo.getPassword())){
            user.setPassword(updatedUserInfo.getPassword());
        }
        if(StringUtils.isNotEmpty(updatedUserInfo.getPhone())) {
            user.setPhone(updatedUserInfo.getPhone());
        }
        if(updatedUserInfo.getJobStatus() != null){
            user.setJobStatus(updatedUserInfo.getJobStatus());
        }

        userRepository.save(user);
    }

    @Override
    public Page<User> findAllUsersByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void changeIsActiveStatus(Long userId, Boolean isActive) throws IllegalArgumentException{
        User user = userRepository.queryUserById(userId);
        Assert.notNull(user, "user not existed");
        user.setIsActive(isActive);
        userRepository.save(user);
    }

    @Override
    public void touchUserAccount(Long userId) {
        User user = userRepository.queryUserById(userId);
        user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }
}
